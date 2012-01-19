package manufacturingPlant.views;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import manufacturingPlant.models.*;

/**
 * The application's main frame.
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {

	private ActionListener controller;
	
    public MainView(ActionListener controller) {
		super("Use Case Industries ManufacturingPlant");
		this.controller = controller;
		initComponents();
		this.add(mainPanel);
		this.setBounds(200, 50, 750, 650);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.setVisible(true);
	}
	
	private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        logLabel = new javax.swing.JLabel();
        newOrderPanel = new javax.swing.JPanel();
        customerTextField = new javax.swing.JTextField();
        customerLabel = new javax.swing.JLabel();
        addedProductsLabel = new javax.swing.JLabel();
        amountSpinner = new javax.swing.JSpinner();
        addProductButton = new javax.swing.JButton();
        amountLabel = new javax.swing.JLabel();
        productList = new javax.swing.JComboBox();
        productToAdd = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        addedProductsList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        addedAmountsList = new javax.swing.JList();
        addOrderButton = new javax.swing.JButton();
        ordersOverviewPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ordersList = new javax.swing.JList();

        mainPanel.setName("mainPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        logArea.setColumns(20);
        logArea.setEditable(false);
        logArea.setRows(5);
        logArea.setWrapStyleWord(true);
        logArea.setName("logArea"); // NOI18N
        jScrollPane1.setViewportView(logArea);

        logLabel.setText("Log"); // NOI18N
        logLabel.setName("logLabel"); // NOI18N

        newOrderPanel.setName("newOrderPanel"); // NOI18N

        customerTextField.setName("customerTextField"); // NOI18N

        customerLabel.setText("Customer"); // NOI18N
        customerLabel.setName("customerLabel"); // NOI18N

        addedProductsLabel.setText("Products"); // NOI18N
        addedProductsLabel.setName("addedProductsLabel"); // NOI18N

        amountSpinner.setName("amountSpinner"); // NOI18N

        addProductButton.setText("Add product"); // NOI18N
        addProductButton.setName("addProductButton"); // NOI18N
        addProductButton.setActionCommand("newProduct");
        addProductButton.addActionListener(controller);

        amountLabel.setText("Amount"); // NOI18N
        amountLabel.setName("amountLabel"); // NOI18N

        productList.setName("productList"); // NOI18N

        productToAdd.setText("Product"); // NOI18N
        productToAdd.setName("productToAdd"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        addedProductsList.setName("addedProductsList"); // NOI18N
        jScrollPane2.setViewportView(addedProductsList);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        addedAmountsList.setName("addedAmountsList"); // NOI18N
        jScrollPane3.setViewportView(addedAmountsList);

        addOrderButton.setText("Add Order"); // NOI18N
        addOrderButton.setName("addOrderButton"); // NOI18N
        addOrderButton.setActionCommand("newOrder");
        addOrderButton.addActionListener(controller);

        javax.swing.GroupLayout newOrderPanelLayout = new javax.swing.GroupLayout(newOrderPanel);
        newOrderPanel.setLayout(newOrderPanelLayout);
        newOrderPanelLayout.setHorizontalGroup(
            newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerLabel)
                    .addComponent(addedProductsLabel)
                    .addComponent(amountLabel)
                    .addComponent(productToAdd))
                .addGap(18, 18, 18)
                .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addGroup(newOrderPanelLayout.createSequentialGroup()
                        .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(newOrderPanelLayout.createSequentialGroup()
                                .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(addProductButton))
                            .addComponent(productList, javax.swing.GroupLayout.Alignment.LEADING, 0, 213, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(addOrderButton))))
                .addContainerGap())
        );
        newOrderPanelLayout.setVerticalGroup(
            newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(addedProductsLabel))
                .addGap(18, 18, 18)
                .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productToAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountLabel)
                    .addComponent(addProductButton)
                    .addComponent(addOrderButton))
                .addContainerGap())
        );

        ordersOverviewPanel.setName("ordersOverviewPanel"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        ordersList.setName("ordersList"); // NOI18N
        jScrollPane4.setViewportView(ordersList);

        javax.swing.GroupLayout ordersOverviewPanelLayout = new javax.swing.GroupLayout(ordersOverviewPanel);
        ordersOverviewPanel.setLayout(ordersOverviewPanelLayout);
        ordersOverviewPanelLayout.setHorizontalGroup(
            ordersOverviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordersOverviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );
        ordersOverviewPanelLayout.setVerticalGroup(
            ordersOverviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordersOverviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(newOrderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ordersOverviewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ordersOverviewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newOrderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

    private javax.swing.JButton addOrderButton;
    private javax.swing.JButton addProductButton;
    private javax.swing.JList addedAmountsList;
    private javax.swing.JLabel addedProductsLabel;
    private javax.swing.JList addedProductsList;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JSpinner amountSpinner;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JTextField customerTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea logArea;
    private javax.swing.JLabel logLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel newOrderPanel;
    private javax.swing.JList ordersList;
    private javax.swing.JPanel ordersOverviewPanel;
    private javax.swing.JComboBox productList;
    private javax.swing.JLabel productToAdd;

    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private Map<Product, Integer> productsOnNewOrder = new HashMap<Product, Integer>();
    
    //Own getters and setters
    public JButton getAddOrderButton() {
    	return addOrderButton;
    }
    
    public JButton getAddProductButton() {
    	return addProductButton;
    }
    
    public String getCustomer() {
        return customerTextField.getText();
    }
    
    public Product getNewProductValue() {
    	return (Product) productList.getSelectedItem();
    }
    
    public int getNewAmountValue() {
    	return (Integer) amountSpinner.getValue();
    }

    public Map<Product, Integer> getProductsOnNewOrder() {
        return productsOnNewOrder;
    }
    
    public void setProductsOnNewOrder(Product product, int amount) {
    	if(productsOnNewOrder.containsKey(product)) {
    		productsOnNewOrder.put(product, productsOnNewOrder.get(product) + amount);
    	} else {
    		productsOnNewOrder.put(product, amount);
    	}
    	
    	updateProductsOnNewOrderList();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        updateProductList();
    }

    public void setOrdersList(ArrayList<Order> orders) {
        this.orders = orders;
        updateOrdersList();
    }

    //Own methodes
    private void updateProductList() {
        productList.setModel(new javax.swing.DefaultComboBoxModel(products.toArray()));
    }
    
    private void updateProductsOnNewOrderList() {
        ArrayList<Product> newProducts = new ArrayList<Product>();
        ArrayList<Integer> newAmounts = new ArrayList<Integer>();
        
        for(Product newProduct : productsOnNewOrder.keySet()) {
        	newProducts.add(newProduct);
        	newAmounts.add(productsOnNewOrder.get(newProduct));
        }
    	
        addedProductsList.setListData(newProducts.toArray());
        addedAmountsList.setListData(newAmounts.toArray());
    }

    private void updateOrdersList() {
        String[] customers = new String[orders.size()];

        for(int i = 0; i < orders.size(); i++) {
            customers[i] = orders.get(i).getCustomer();
        }

        ordersList.setModel(new javax.swing.DefaultComboBoxModel(customers));
    }
    
    public void resetNewProduct() {
    	productList.setSelectedIndex(0);
    	amountSpinner.setValue(new Integer(0));
    }
    
    public void log(String msg) {
    	logArea.append(msg + "\n");
    }
}