package com.threadco.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Applies Thread & Co. pricing guardrails to a price proposed by the pricing agent. */
public class PriceDecisionService {

    static final BigDecimal MAX_CHANGE = new BigDecimal("0.10");   // at most +/-10% per decision
    static final BigDecimal MARGIN_FLOOR = new BigDecimal("0.35"); // keep at least 35% margin
    static final BigDecimal APPROVAL_THRESHOLD = new BigDecimal("0.08");

    public PriceDecision decide(String sku, String store, BigDecimal current, BigDecimal unitCost, BigDecimal proposed) {
        BigDecimal change = proposed.subtract(current).divide(current, 4, RoundingMode.HALF_UP);
        if (change.abs().compareTo(MAX_CHANGE) > 0) {
            return PriceDecision.rejected(sku, store, "exceeds maximum change");
        }
        BigDecimal margin = proposed.subtract(unitCost).divide(proposed, 4, RoundingMode.HALF_UP);
        if (margin.compareTo(MARGIN_FLOOR) < 0) {
            return PriceDecision.rejected(sku, store, "below margin floor");
        }
        boolean needsApproval = change.abs().compareTo(APPROVAL_THRESHOLD) > 0;
        return needsApproval ? PriceDecision.pendingApproval(sku, store, proposed)
                             : PriceDecision.applied(sku, store, proposed);
    }
}
