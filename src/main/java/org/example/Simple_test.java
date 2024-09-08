package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Simple_test {

    @BeforeEach
    public void setSettings() throws FileNotFoundException, IOException {
        actions.setSettings();
        actions.setPause();
    }


    @AfterEach
    public void endOfWork() {
        actions.endOfWork();
    }

    @Test
    public void start_of_test() {
         actions.gotoSite();
         actions.check_list_of_goods();
         actions.useFilter();
         actions.makeADeal();
    }


}
