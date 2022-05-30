package ru.vsu.cs.okshina_v_a;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ru.vsu.cs.okshina_v_a.utils.ListUtils;
import ru.vsu.cs.okshina_v_a.utils.JTableUtils;
import ru.vsu.cs.okshina_v_a.utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInputX;
    private JButton buttonInputXFromFile;
    private JTable tableInputY;
    private JButton buttonInputYFromFile;
    private JTable tableOutput;
    private JButton buttonInputXRandom;
    private JButton buttonInputYRandom;
    private JButton buttonSaveX;
    private JButton buttonSaveY;
    private JButton buttonExecute;
    private JButton buttonSaveResult;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private final JMenuBar menuBarMain;
    private final JMenu menuLookAndFeel;

    public FrameMain() {
        this.setTitle("Task 3");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(700, 100, 300, 300);
        this.setResizable(true);
        this.pack();

        JTableUtils.initJTableForArray(tableInputX, 30, true, true, false, true, 25, 15);
        JTableUtils.initJTableForArray(tableInputY, 30, true, true, false, true, 25, 15);
        JTableUtils.initJTableForArray(tableOutput, 30, true, true, false, false, 25, 15);


        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);

        buttonInputXFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = ListUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        assert arr != null;
                        JTableUtils.writeArrayToJTable(tableInputX, arr, "%d");
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonInputYFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = ListUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        assert arr != null;
                        JTableUtils.writeArrayToJTable(tableInputY, arr, "%d");
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSaveX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        Integer[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInputX, Integer.class, Integer::parseInt, false, 0);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ListUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSaveY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        Integer[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInputY, Integer.class, Integer::parseInt, false, 0);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ListUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arr1 = JTableUtils.readIntArrayFromJTable(tableInputX);
                    int[] arr2 = JTableUtils.readIntArrayFromJTable(tableInputY);

                    MySimpleQueue<Integer> myQueue1 = MySimpleQueue.arrayToMyQueue(arr1);
                    MySimpleQueue<Integer> myQueue2 = MySimpleQueue.arrayToMyQueue(arr2);

                    int[] resultWithMyQueue = ExtractingQueue.transformMyQueue(myQueue1, myQueue2);

                    JTableUtils.writeArrayToJTable(tableOutput, resultWithMyQueue, "%d");
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonInputXRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = ListUtils.createRandomIntMatrix(
                            tableInputX.getRowCount(), tableInputX.getColumnCount(), 0, 100);
                    JTableUtils.writeArrayToJTable(tableInputX, matrix, "%d");
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonInputYRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = ListUtils.createRandomIntMatrix(
                            tableInputY.getRowCount(), tableInputY.getColumnCount(), 0, 100);
                    JTableUtils.writeArrayToJTable(tableInputY, matrix, "%d");
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSaveResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        Integer[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput, Integer.class, Integer::parseInt, false, 0);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ListUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        buttonInputXFromFile = new JButton();
        buttonInputXFromFile.setText("Загрузить очередь Х из файла");
        panelMain.add(buttonInputXFromFile, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonInputYFromFile = new JButton();
        buttonInputYFromFile.setText("Загрузить очередь У из файла");
        panelMain.add(buttonInputYFromFile, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonInputXRandom = new JButton();
        buttonInputXRandom.setText("Очередь Х (случайно)");
        panelMain.add(buttonInputXRandom, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonInputYRandom = new JButton();
        buttonInputYRandom.setText("Очередь У (случайно)");
        panelMain.add(buttonInputYRandom, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonSaveX = new JButton();
        buttonSaveX.setText("Сохранить очередь Х в файл");
        panelMain.add(buttonSaveX, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonSaveY = new JButton();
        buttonSaveY.setText("Сохранить очередь У в файл");
        panelMain.add(buttonSaveY, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonExecute = new JButton();
        buttonExecute.setText("Выполнить");
        panelMain.add(buttonExecute, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableInputX = new JTable();
        scrollPane1.setViewportView(tableInputX);
        final JScrollPane scrollPane2 = new JScrollPane();
        panelMain.add(scrollPane2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableInputY = new JTable();
        scrollPane2.setViewportView(tableInputY);
        final JScrollPane scrollPane3 = new JScrollPane();
        panelMain.add(scrollPane3, new GridConstraints(5, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableOutput = new JTable();
        scrollPane3.setViewportView(tableOutput);
        buttonSaveResult = new JButton();
        buttonSaveResult.setText("Сохранить результат в файл");
        panelMain.add(buttonSaveResult, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}

