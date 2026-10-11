# Thread & Co. — Real-time Pricing (demo)

> **Demo repository. Intentionally contains outdated, vulnerable dependencies and insecure code. Do not deploy.**
> Fictional retailer, built to demonstrate IBM Concert risk analysis for the IBM TechXchange Service Partner breakout.

Thread & Co. runs 100+ stores, a central warehouse and an online store. During large events (e.g. a conference
bringing thousands of people to Atlanta) prices follow live demand, inside pricing guardrails.

## What's in the repo

| Folder | What it is |
|---|---|
| `docs/` | Demo storefront page (live prices, smart mirror, "Why this price?", pricing decisions). Served with GitHub Pages. Prices are simulated in the browser. |
| `storefront/` | Web store and smart-mirror UI (TypeScript). Subscribes to live price updates streamed from Kafka. |
| `pricing-service/` | Java service. Applies the guardrails (max 10% change, 35% margin floor, approval above 8%), publishes decisions to Kafka and reads price history. |

```
POS / e-commerce / inventory ──► Kafka (demand.signals) ──► pricing-service ──► Kafka (price.updates) ──► storefront + smart mirrors
                                                               │
                                                               └──► price history (event_outcomes)
```

## Open the demo page

- Locally: open `docs/index.html` in a browser.
- Online: Settings → Pages → Source: *Deploy from a branch*, Branch: `main`, Folder: `/docs`.
