package org.e2e.flows.mec.countries.germany;

import org.e2e.flows.mec.MecCommon;
import org.e2e.flows.mec.countries.germany.locators.MecDeLocator;

public class MecDeFlow extends MecCommon<MecDeFlow> implements MecDeLocator<MecDeFlow> {

    @Override
    public MecDeFlow fillIBAN(String iban) {
        byInputIban.sendKeys(iban);
        return this;
    }
}
