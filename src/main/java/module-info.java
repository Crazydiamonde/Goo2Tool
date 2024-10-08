module com.crazine.goo2tool {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;

    exports com.crazine.goo2tool;
    exports com.crazine.goo2tool.gui;
    exports com.crazine.goo2tool.saveFile;
    exports com.crazine.goo2tool.addinFile;
    exports com.crazine.goo2tool.properties;
    exports com.crazine.goo2tool.functional;
    exports com.crazine.goo2tool.islands;

}