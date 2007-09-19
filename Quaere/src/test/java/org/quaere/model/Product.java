package org.quaere.model;

public class Product {
    private int productID;
    private String productName;
    private String category;
    private double unitPrice;
    private int unitsInStock;

    public Product(int prouductID, String productName, String category, double unitPrice, int unitsInStock) {
        this.productID = prouductID;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    @Override
    public String toString() {
        return String.format("Product (%s): [%s, %s, %s, %s, %s]", hashCode(), productID, productName, category, unitPrice, unitsInStock);
    }

    public static Product[] getAllProducts() {
        Product[] products = {
                new Product(1, "Chai", "Beverages", 18.0000, 39),
                new Product(2, "Chang", "Beverages", 19.0000, 17),
                new Product(3, "Aniseed Syrup", "Condiments", 10.0000, 13),
                new Product(4, "Chef Anton's Cajun Seasoning", "Condiments", 22.0000, 53),
                new Product(5, "Chef Anton's Gumbo Mix", "Condiments", 21.3500, 0),
                new Product(6, "Grandma's Boysenberry Spread", "Condiments", 25.0000, 120),
                new Product(7, "Uncle Bob's Organic Dried Pears", "Produce", 30.0000, 15),
                new Product(8, "Northwoods Cranberry Sauce", "Condiments", 40.0000, 6),
                new Product(9, "Mishi Kobe Niku", "Meat/Poultry", 97.0000, 29),
                new Product(10, "Ikura", "Seafood", 31.0000, 31),
                new Product(11, "Queso Cabrales", "Dairy Products", 21.0000, 22),
                new Product(12, "Queso Manchego La Pastora", "Dairy Products", 38.0000, 86),
                new Product(13, "Konbu", "Seafood", 6.0000, 24),
                new Product(14, "Tofu", "Produce", 23.2500, 35),
                new Product(15, "Genen Shouyu", "Condiments", 15.5000, 39),
                new Product(16, "Pavlova", "Confections", 17.4500, 29),
                new Product(17, "Alice Mutton", "Meat/Poultry", 39.0000, 0),
                new Product(18, "Carnarvon Tigers", "Seafood", 62.5000, 42),
                new Product(19, "Teatime Chocolate Biscuits", "Confections", 9.2000, 25),
                new Product(20, "Sir Rodney's Marmalade", "Confections", 81.0000, 40),
                new Product(21, "Sir Rodney's Scones", "Confections", 10.0000, 3),
                new Product(22, "Gustaf's Knäckebröd", "Grains/Cereals", 21.0000, 104),
                new Product(23, "Tunnbröd", "Grains/Cereals", 9.0000, 61),
                new Product(24, "Guaraná Fantástica", "Beverages", 4.5000, 20),
                new Product(25, "NuNuCa Nuß-Nougat-Creme", "Confections", 14.0000, 76),
                new Product(26, "Gumbär Gummibärchen", "Confections", 31.2300, 15),
                new Product(27, "Schoggi Schokolade", "Confections", 43.9000, 49),
                new Product(28, "Rössle Sauerkraut", "Produce", 45.6000, 26),
                new Product(29, "Thüringer Rostbratwurst", "Meat/Poultry", 123.7900, 0),
                new Product(30, "Nord-Ost Matjeshering", "Seafood", 25.8900, 10),
                new Product(31, "Gorgonzola Telino", "Dairy Products", 12.5000, 0),
                new Product(32, "Mascarpone Fabioli", "Dairy Products", 32.0000, 9),
                new Product(33, "Geitost", "Dairy Products", 2.5000, 112),
                new Product(34, "Sasquatch Ale", "Beverages", 14.0000, 111),
                new Product(35, "Steeleye Stout", "Beverages", 18.0000, 20),
                new Product(36, "Inlagd Sill", "Seafood", 19.0000, 112),
                new Product(37, "Gravad lax", "Seafood", 26.0000, 11),
                new Product(38, "Côte de Blaye", "Beverages", 263.5000, 17),
                new Product(39, "Chartreuse verte", "Beverages", 18.0000, 69),
                new Product(40, "Boston Crab Meat", "Seafood", 18.4000, 123),
                new Product(41, "Jack's New England Clam Chowder", "Seafood", 9.6500, 85),
                new Product(42, "Singaporean Hokkien Fried Mee", "Grains/Cereals", 14.0000, 26),
                new Product(43, "Ipoh Coffee", "Beverages", 46.0000, 17),
                new Product(44, "Gula Malacca", "Condiments", 19.4500, 27),
                new Product(45, "Rogede sild", "Seafood", 9.5000, 5),
                new Product(46, "Spegesild", "Seafood", 12.0000, 95),
                new Product(47, "Zaanse koeken", "Confections", 9.5000, 36),
                new Product(48, "Chocolade", "Confections", 12.7500, 15),
                new Product(49, "Maxilaku", "Confections", 20.0000, 10),
                new Product(50, "Valkoinen suklaa", "Confections", 16.2500, 65),
                new Product(51, "Manjimup Dried Apples", "Produce", 53.0000, 20),
                new Product(52, "Filo Mix", "Grains/Cereals", 7.0000, 38),
                new Product(53, "Perth Pasties", "Meat/Poultry", 32.8000, 0),
                new Product(54, "Tourtière", "Meat/Poultry", 7.4500, 21),
                new Product(55, "Pâté chinois", "Meat/Poultry", 24.0000, 115),
                new Product(56, "Gnocchi di nonna Alice", "Grains/Cereals", 38.0000, 21),
                new Product(57, "Ravioli Angelo", "Grains/Cereals", 19.5000, 36),
                new Product(58, "Escargots de Bourgogne", "Seafood", 13.2500, 62),
                new Product(59, "Raclette Courdavault", "Dairy Products", 55.0000, 79),
                new Product(60, "Camembert Pierrot", "Dairy Products", 34.0000, 19),
                new Product(61, "Sirop d'érable", "Condiments", 28.5000, 113),
                new Product(62, "Tarte au sucre", "Confections", 49.3000, 17),
                new Product(63, "Vegie-spread", "Condiments", 43.9000, 24),
                new Product(64, "Wimmers gute Semmelknödel", "Grains/Cereals", 33.2500, 22),
                new Product(65, "Louisiana Fiery Hot Pepper Sauce", "Condiments", 21.0500, 76),
                new Product(66, "Louisiana Hot Spiced Okra", "Condiments", 17.0000, 4),
                new Product(67, "Laughing Lumberjack Lager", "Beverages", 14.0000, 52),
                new Product(68, "Scottish Longbreads", "Confections", 12.5000, 6),
                new Product(69, "Gudbrandsdalsost", "Dairy Products", 36.0000, 26),
                new Product(70, "Outback Lager", "Beverages", 15.0000, 15),
                new Product(71, "Flotemysost", "Dairy Products", 21.5000, 26),
                new Product(72, "Mozzarella di Giovanni", "Dairy Products", 34.8000, 14),
                new Product(73, "Röd Kaviar", "Seafood", 15.0000, 101),
                new Product(74, "Longlife Tofu", "Produce", 10.0000, 4),
                new Product(75, "Rhönbräu Klosterbier", "Beverages", 7.7500, 125),
                new Product(76, "Lakkalikööri", "Beverages", 18.0000, 57),
                new Product(77, "Original Frankfurter grüne Soße", "Condiments", 13.0000, 32)
        };
        return products;
    }
}
