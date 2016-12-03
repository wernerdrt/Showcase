package org.educama.shipment.control;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShipmentCaseControlService {

    private final String SHIPMENTCASEKEY = "shipment";

    @Autowired
    private CaseService caseService;

    /**
     * A CaseInstance is created by the the casekey {@link #SHIPMENTCASEKEY} and a UUID as business key.
     * That business key will be used as transport id in both data model.
     *
     * @return by CMMN engine generated CaseInstance
     */
    public CaseInstance create() {
        String businessKey = UUID.randomUUID().toString();
        CaseInstance caseInstance = caseService.createCaseInstanceByKey(SHIPMENTCASEKEY, businessKey);

        return caseInstance;
    }
}
