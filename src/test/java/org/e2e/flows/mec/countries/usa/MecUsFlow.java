package org.e2e.flows.mec.countries.usa;

import org.e2e.flows.mec.MecCommon;
import org.e2e.flows.mec.countries.usa.locators.MecUsLocator;

public class MecUsFlow extends MecCommon<MecUsFlow> implements MecUsLocator<MecUsFlow> {

    @Override
    public MecUsFlow fillRoutingNumber(String routingNumber) {
        byInputRoutingNumber.sendKeys(routingNumber);
        return this;
    }
}
