package org.e2e.flows.mec.countries.australia;

import org.e2e.flows.mec.MecCommon;
import org.e2e.flows.mec.countries.australia.locators.MecAuLocator;

public class MecAuFlow extends MecCommon<MecAuFlow> implements MecAuLocator<MecAuFlow> {

    @Override
    public MecAuFlow fillBSB(String bsb) {
        byInputBSB.sendKeys(bsb);
        return this;
    }
}
