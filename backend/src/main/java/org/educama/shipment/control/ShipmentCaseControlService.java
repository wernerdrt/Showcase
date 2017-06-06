package org.educama.shipment.control;

import java.util.UUID;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.educama.shipment.process.ShipmentCaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Control service for shipments.
 */
@Service
public class ShipmentCaseControlService {

    @Autowired
    private CaseService caseService;

    /**
     * Creates a new CaseInstance with a generated business key.
     *
     * @return the new CaseInstance
     */
    public CaseInstance create() {
        String businessKey = UUID.randomUUID().toString();
        CaseInstance caseInstance = caseService.createCaseInstanceByKey(ShipmentCaseConstants.SHIPMENTCASEKEY, businessKey);

        return caseInstance;
    }
}
