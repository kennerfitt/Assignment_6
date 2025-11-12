# SE333 – Assignment 6 – UI Testing

## Repository
[GitHub Repository Link](https://github.com/kennerfitt/Assignment_6)

## Part A & B – Traditional Playwright Testing
- Implemented in `src/test/java/org/example/PlaywrightTest.java`
- Automates the full purchase flow for the DePaul University bookstore.
- Includes filtering by brand, color, and price, adding to cart, checkout flow, and assertions for:
    - Product details (name, price, SKU, description)
    - Cart validation
    - Promo code rejection
    - Checkout navigation
- Videos of test runs are recorded automatically and uploaded via GitHub Actions.

## Part 4 – AI-Assisted (LLM) Playwright Testing
- Implemented in `src/test/java/playwrightLLM/PlaywrightLLMTest.java`
- Simplified test flow generated with Playwright MCP / AI-assisted prompting.
- Verifies basic page navigation and item visibility for “JBL Quantum True Wireless”.

## Reflection: Manual vs AI-Assisted
| Aspect | Traditional Testing | AI-Assisted Testing |
|--------|--------------------|--------------------|
| **Ease of Writing** | Required detailed manual scripting and debugging | Faster using AI-generated code |
| **Accuracy** | High precision and control over assertions | May need manual fine-tuning |
| **Maintenance** | Easy to modify specific steps | Depends on how the AI generated the flow |
| **Reliability** | Deterministic and reproducible | Can vary depending on generated selectors |

Overall, both approaches automate the same workflow successfully, but the AI-assisted version saves time at the cost of fine-grained control.

## Continuous Integration (CI)
- The repository is configured with GitHub Actions (`.github/workflows/playwright.yml`)
- CI automatically runs both test suites (`PlaywrightTest` and `PlaywrightLLMTest`) on every push
- Test videos are stored under `/videos` and uploaded as artifacts
