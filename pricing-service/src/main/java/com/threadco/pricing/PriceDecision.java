package com.threadco.pricing;

import java.math.BigDecimal;

public class PriceDecision {
    public final String sku, store, status, reason;
    public final BigDecimal price;

    private PriceDecision(String sku, String store, String status, BigDecimal price, String reason) {
        this.sku = sku; this.store = store; this.status = status; this.price = price; this.reason = reason;
    }
    static PriceDecision applied(String sku, String store, BigDecimal p)         { return new PriceDecision(sku, store, "APPLIED", p, null); }
    static PriceDecision pendingApproval(String sku, String store, BigDecimal p) { return new PriceDecision(sku, store, "PENDING_APPROVAL", p, null); }
    static PriceDecision rejected(String sku, String store, String why)          { return new PriceDecision(sku, store, "REJECTED", null, why); }
}
