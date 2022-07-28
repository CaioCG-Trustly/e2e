package org.e2e.flows.mec.countries.greatbritish;

import org.e2e.flows.mec.countries.greatbritish.locators.MecGbLocator;
import org.e2e.flows.mec.MecCommon;

public class MecGbFlow extends MecCommon<MecGbFlow> implements MecGbLocator<MecGbFlow> {

    @Override
    public MecGbFlow fillSortCode(String sortCode) {
        byInputSortCode.sendKeys(sortCode);
        return this;
    }
}
