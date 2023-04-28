package edu.scau.mis.pos629.view;

import edu.scau.mis.pos629.vo.SaleItemVo;
import org.springframework.stereotype.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * NextGenPos案例GUI界面
 * 仅供OOAD课程测试使用
 */

@Component
public class SaleFrame extends JFrame implements ActionListener {
    private final Register register;

    private static final long serialVersionUID = 1L;

    private JMenuItem jItemExit;
    private JPanel jToolBarPane,jMainPane,jPayPane;
    private JScrollPane jDataListPane;
    private JTextField jItemIdTextField,jItemQuantityTextField,jPayableTextField,jPaidTextField,jChangeTextField;
    private JButton jEnterItemButton, jEndSaleButton, jStartButton, jPayButton;
    private final List<SaleItemVo> tableModels = new ArrayList<>();
    private final Font fontLabel = new Font("微软雅黑",Font.PLAIN,18);
    private final Font fontButton = new Font("微软雅黑",Font.PLAIN,16);
    private final Font fontText = new Font("微软雅黑",Font.PLAIN,28);

    /**
     * 初始化Frame
     */
    public SaleFrame(Register register) {
        this.setSize(900, 730);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(getJMenuBar());
        this.setContentPane(getJContentPane());
        this.setTitle("OOAD课程案例:NextGenPOS");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.register = register;
    }

    /**
     * 菜单面板
     * @return 面板
     */
    public JMenuBar getJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jSystemMenu = new JMenu("系统");
        jSystemMenu.setFont(fontLabel);
        JMenu jVIPMenu = new JMenu("会员");
        jVIPMenu.setFont(fontLabel);
        JMenu jPaymentMenu = new JMenu("支付");
        jPaymentMenu.setFont(fontLabel);
        jItemExit = new JMenuItem("退出");
        jItemExit.setFont(fontLabel);
        jItemExit.addActionListener(this);
        JMenuItem jItemVipCard = new JMenuItem("会员管理");
        jItemVipCard.setFont(fontLabel);
        jItemVipCard.addActionListener(this);
        JMenuItem jItemCreditPayment = new JMenuItem("信用卡支付");
        jItemCreditPayment.setFont(fontLabel);
        jItemCreditPayment.addActionListener(this);
        jSystemMenu.add(jItemExit);
        jVIPMenu.add(jItemVipCard);
        jPaymentMenu.add(jItemCreditPayment);
        jMenuBar.add(jSystemMenu);
        jMenuBar.add(jVIPMenu);
        jMenuBar.add(jPaymentMenu);
        return jMenuBar;
    }

    /**
     * 窗口面板
     * @return 面板
     */
    public JPanel getJContentPane() {
        JPanel jContentPane = new JPanel();
        jContentPane.setLayout(new BorderLayout());
        jContentPane.add(getJToolBarPane(), BorderLayout.NORTH);
        jContentPane.add(getJMainPane(), BorderLayout.CENTER);
        jContentPane.add(getJPayPane(), BorderLayout.WEST);
        return jContentPane;
    }

    /**
     * 工具栏面板
     * @return 面板
     */
    public JPanel getJToolBarPane() {
        if (jToolBarPane == null) {
            jToolBarPane = new JPanel();
        }
        JToolBar jToolBar = new JToolBar();
        jToolBar.setPreferredSize(new Dimension(880, 49));
        jToolBar.setLayout(new GridLayout(1, 6));
        jItemIdTextField = new JTextField();
        jItemIdTextField.setFont(fontText);
        jItemIdTextField.setHorizontalAlignment(JTextField.CENTER);
        jItemIdTextField.setEditable(false);
        jItemIdTextField.addActionListener(this);
        jItemQuantityTextField = new JTextField("1");
        jItemQuantityTextField.setHorizontalAlignment(JTextField.CENTER);
        jItemQuantityTextField.setFont(fontText);
        jItemQuantityTextField.setEditable(false);
        jEnterItemButton = new JButton("EnterItem(F2)");
        jEnterItemButton.setMnemonic(java.awt.event.KeyEvent.VK_F2);
        jEnterItemButton.setEnabled(false);
        jEnterItemButton.addActionListener(this);
        jEndSaleButton = new JButton("EndSale(F3)");
        jEndSaleButton.setMnemonic(java.awt.event.KeyEvent.VK_F3);
        jEndSaleButton.setEnabled(false);
        jEndSaleButton.addActionListener(this);
        JLabel jSnLabel = new JLabel("产品编码",JLabel.CENTER);
        jSnLabel.setFont(fontLabel);
        jToolBar.add(jSnLabel);
        jToolBar.add(jItemIdTextField);
        JLabel jQuantityLabel = new JLabel("数量",JLabel.CENTER);
        jQuantityLabel.setFont(fontLabel);
        jToolBar.add(jQuantityLabel);
        jEndSaleButton.setFont(fontButton);
        jEnterItemButton.setFont(fontButton);
        jToolBar.add(jItemQuantityTextField);
        jToolBar.add(jEnterItemButton);
        jToolBar.add(jEndSaleButton);
        jToolBarPane.add(jToolBar);
        return jToolBarPane;
    }

    /**
     * 主内容面板
     * @return 面板
     */
    public JPanel getJMainPane() {
        jMainPane = new JPanel();
        jMainPane.setLayout(new BorderLayout());
        jMainPane.add(getJDataListPane(), BorderLayout.CENTER);
        return jMainPane;
    }

    /**
     * 数据表格面板
     * @return 面板
     */
    public JScrollPane getJDataListPane() {
        if (jDataListPane == null) {
            jDataListPane = new JScrollPane();
        }
        jDataListPane.setViewportView(getJTable());
        return jDataListPane;
    }

    /**
     * 数据表格
     * @return 面板
     */
    public JTable getJTable() {
        // 定义table
        Object[] title = { "商品编码", "商品名称", "单价", "数量" };
        int count = 9;
        Object[][] a = new Object[count][4];
        int row = 0;
        // 迭代表格数据模型
        for (SaleItemVo vo : tableModels) {
            a[row][0] = vo.getItemSn();
            a[row][1] = vo.getProductName();
            a[row][2] = vo.getPrice().toString();
            a[row][3] = vo.getQuantity();
            row++;
        }
        JTable table = new JTable(a, title);
        // 设置数据内容渲染居中
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);
        // 设置数据行高字体
        table.setRowHeight(60);
        table.setFont(fontLabel);
        // 设置标题栏
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(),61));
        tableHeader.setFont(fontLabel);
        return table;
    }

    /**
     * 左侧支付面板
     * @return 面板
     */
    public JPanel getJPayPane() {
        if (jPayPane == null) {
            jPayPane = new JPanel();
            jPayPane.setPreferredSize(new Dimension(200, 500));
            jPayPane.setLayout(new GridLayout(10, 1));
        }
        JLabel jCustomerLabel = new JLabel("顾客编号", JLabel.CENTER);
        jCustomerLabel.setFont(fontLabel);
        JLabel jPayableLabel = new JLabel("总金额", JLabel.CENTER);
        jPayableLabel.setFont(fontLabel);
        JLabel jPaidLabel = new JLabel("支付金额", JLabel.CENTER);
        jPaidLabel.setFont(fontLabel);
        JLabel jChangeLabel = new JLabel("找零", JLabel.CENTER);
        jChangeLabel.setFont(fontLabel);
        JTextField jCustomerTextField = new JTextField();
        jCustomerTextField.setEditable(false);
        jPayableTextField = new JTextField("0.00");
        jPayableTextField.setHorizontalAlignment(JTextField.RIGHT);
        jPayableTextField.setForeground(Color.RED);
        jPayableTextField.setFont(fontText);
        jPayableTextField.setEditable(false);
        jPaidTextField = new JTextField("0.00");
        jPaidTextField.setHorizontalAlignment(JTextField.RIGHT);
        jPaidTextField.setForeground(Color.BLUE);
        jPaidTextField.setFont(fontText);
        jPaidTextField.setEditable(false);
        jChangeTextField = new JTextField("0.00");
        jChangeTextField.setHorizontalAlignment(JTextField.RIGHT);
        jChangeTextField.setFont(fontText);
        jChangeTextField.setForeground(Color.GREEN);
        jChangeTextField.setEditable(false);
        jStartButton = new JButton("MakeNewSale(F1)");
        jStartButton.addActionListener(this);
        jStartButton.setMnemonic(java.awt.event.KeyEvent.VK_F1); // Alt+F1
        jPayButton = new JButton("MakePayment(F4)");
        jPayButton.setMnemonic(java.awt.event.KeyEvent.VK_F4); // Alt+F4
        jPayButton.setEnabled(false);
        jPayButton.addActionListener(this);
        jStartButton.setFont(fontButton);
        jPayButton.setFont(fontButton);
        jPayPane.add(jStartButton);
        jPayPane.add(jCustomerLabel);
        jPayPane.add(jCustomerTextField);
        jPayPane.add(jPayableLabel);
        jPayPane.add(jPayableTextField);
        jPayPane.add(jPaidLabel);
        jPayPane.add(jPaidTextField);
        jPayPane.add(jChangeLabel);
        jPayPane.add(jChangeTextField);
        jPayPane.add(jPayButton);
        return jPayPane;
    }

    /**
     * 更新表格模型，刷新表格面板
     */
    public void UpdateTableModelAndRefreshTablePanel(SaleItemVo saleItemVo) {
        // 迭代tableModels是否存在相同商品，如存在，更新数量
        boolean isEntered = false;
        for (SaleItemVo tableModel : tableModels) {
            if (tableModel.getItemSn().equals(saleItemVo.getItemSn())) {
                int quantityOriginal = tableModel.getQuantity();
                tableModel.setQuantity(quantityOriginal + saleItemVo.getQuantity());
                isEntered = true;
            }
        }
        //如不存在，添加新的vo
        if(!isEntered) {
            tableModels.add(saleItemVo);
        }
        jMainPane.removeAll();
        jMainPane.add(getJDataListPane(), BorderLayout.CENTER);
        this.validate();
    }

    /**
     * 新的销售清除界面
     */
    private void clear() {
        tableModels.clear();
        jMainPane.removeAll();
        jMainPane.add(getJDataListPane(), BorderLayout.CENTER);
        this.validate();
        jItemIdTextField.setText(null);
        jItemQuantityTextField.setText("1");
        jPayableTextField.setText("0.00");
        jPaidTextField.setText("0.00");
        jChangeTextField.setText("0.00");
        jItemIdTextField.setEditable(true);
        jItemQuantityTextField.setEditable(true);
        jEnterItemButton.setEnabled(true);
        jEndSaleButton.setEnabled(true);
        jPaidTextField.setEditable(false);
        jPayButton.setEnabled(false);
    }
    /**
     * 事件监听处理
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jItemExit) {
            System.exit(0);
        } else if (e.getSource() == jStartButton) {
            this.makeNewSale();
        } else if (e.getSource() == jEnterItemButton) {
            this.enterItem();
        } else if (e.getSource() == jEndSaleButton) {
            this.endSale();
        } else if (e.getSource() == jPayButton) {
            this.makePayment();
        } else if (e.getSource() == jItemIdTextField) {
            //绑定回车事件
            this.enterItem();
        }
    }

    /**
     * 开始一次新的销售
     */
    public void makeNewSale() {
        register.makeNewSale();
        this.tableModels.clear();
        this.clear();
        jStartButton.setEnabled(false);
    }

    /**
     * 输入商品
     */
    public void enterItem() {
        String itemSn = jItemIdTextField.getText();
        int quantity;
        String regex = "^\\d*[1-9]\\d*$";
        String quantityText = jItemQuantityTextField.getText();
        if(quantityText.matches(regex)) {
            quantity = Integer.parseInt(quantityText);
            SaleItemVo vo = register.enterItem(itemSn,quantity);
            if (vo != null) {
                this.UpdateTableModelAndRefreshTablePanel(vo);
            } else {
                JOptionPane.showMessageDialog(this,"输入编码错误，未找到商品","警告", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"输入数量格式有误","错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 结束销售
     */
    public void endSale() {
        BigDecimal total = register.endSale();
        jPayableTextField.setText(total.toString());
        jEnterItemButton.setEnabled(false);
        jPaidTextField.setEditable(true);
        jItemIdTextField.setEditable(false);
        jItemQuantityTextField.setEditable(false);
        jPayButton.setEnabled(true);
        jEndSaleButton.setEnabled(false);
    }

    /**
     * 支付
     */
    public void makePayment() {
        BigDecimal total = new BigDecimal(jPayableTextField.getText().trim());
        BigDecimal cash = new BigDecimal("0.00");
        String regex = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
        String cashText = jPaidTextField.getText().trim();
        // 格式校验
        if(cashText.matches(regex)) {
            cash = cash.add(new BigDecimal(cashText));
            if(cash.compareTo(total) >= 0) {
                BigDecimal change = register.makePayment(cash);
                jChangeTextField.setText(change.toString());
                JOptionPane.showMessageDialog(this, this.printBill(total,cash,change), "购物凭证", JOptionPane.INFORMATION_MESSAGE);
                this.jPaidTextField.setEditable(false);
                this.jStartButton.setEnabled(true);
                this.jPayButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this,"支付金额小于总金额","警告", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"输入支付金额格式有误","错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 打印小票
     * @param total 总金额
     * @param cash 支付金额
     * @param change 找零
     */
    private StringBuffer printBill(BigDecimal total, BigDecimal cash, BigDecimal change){
        StringBuffer bill = new StringBuffer("收银成功，清单如下: \n\n");
        bill.append("编码      名称   单价    数量 \n" );
        bill.append("-------------------------------------------- \n" );
        for(SaleItemVo si : tableModels) {
            bill.append(si.getItemSn())
                    .append("      ")
                    .append(si.getProductName()).append("      ")
                    .append(si.getPrice().toString())
                    .append("      ")
                    .append(si.getQuantity())
                    .append("\n");
        }
        bill.append("-------------------------------------------- \n" );
        bill.append("                                合计:").
                append(total.toString())
                .append("元 \n");
        bill.append("  收银:")
                .append(cash.toString())
                .append("元，找零:")
                .append(change.toString())
                .append("元");
        return bill;
    }
}
