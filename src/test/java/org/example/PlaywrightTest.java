package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setupClass() {
        playwright = Playwright.create();
        boolean isCI = System.getenv("CI") != null;
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(isCI));
    }

    @BeforeEach
    void setup() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))
                .setRecordVideoSize(1280, 720));
        page = context.newPage();
    }

    @AfterEach
    void teardown() {
        context.close();
    }

    @AfterAll
    static void teardownClass() {
        browser.close();
        playwright.close();
    }

    @Test
    void testBookstorePurchaseFlow() {
        page.navigate("https://depaul.bncollege.com/");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("earbuds");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).press("Enter");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("brand")).click();
        page.locator(".facet__list.js-facet-list.js-facet-top-values > li:nth-child(3) > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").first().click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color")).click();
        page.locator("#facet-Color > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").first().click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price")).click();
        page.locator("#facet-price > .facet__values > .facet__list > li:nth-child(2) > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless"))).isVisible();
        assertThat(page.getByText("sku").nth(1)).isVisible();
        assertThat(page.getByText("$164.98")).isVisible();
        assertThat(page.getByText("Adaptive noise cancelling")).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items"))).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your Shopping Cart(1 Item)"))).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless"))).isVisible();
        assertThat(page.locator(".bned-cart-item-qty")).isVisible();
        assertThat(page.getByText("$").first()).isVisible();
        page.getByText("FAST In-Store PickupDePaul").click();
        assertThat(page.locator(".bned-cart-header-row.bned-cart-totals-wp")).isVisible();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).fill("TEST");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Promo Code")).click();
        assertThat(page.getByText("The coupon code entered is")).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed To Checkout")).first().click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Create Account"))).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed As Guest")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Contact Information"))).isVisible();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).fill("DJ");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).fill("Kim");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).fill("fake123@gmail.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).press("Tab");
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("United States: +")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).fill("3121112222");
        assertThat(page.getByText("$164.98").nth(2)).isVisible();
        assertThat(page.getByText("$3.00").nth(3)).isVisible();
        assertThat(page.getByText("TBD").nth(2)).isVisible();
        assertThat(page.getByText("$167.98").nth(1)).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
        assertThat(page.getByText("DJ Kim")).isVisible();
        assertThat(page.getByText("fake123@gmail.com")).isVisible();
        assertThat(page.getByText("+13121112222")).isVisible();
        assertThat(page.locator("#bnedPickupPersonForm").getByText("DePaul University Loop Campus")).isVisible();
        assertThat(page.getByText("I'll pick them up")).isVisible();
        assertThat(page.getByText("$164.98").nth(2)).isVisible();
        assertThat(page.getByText("$3.00").nth(3)).isVisible();
        assertThat(page.getByText("TBD").nth(2)).isVisible();
        assertThat(page.getByText("$167.98").nth(1)).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).nth(1)).isVisible();
        assertThat(page.getByText("$164.98").nth(3)).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
        assertThat(page.getByText("$164.98").nth(2)).isVisible();
        assertThat(page.getByText("$3.00").nth(3)).isVisible();
        assertThat(page.getByText("$17.22").nth(1)).isVisible();
        assertThat(page.getByText("$17.22").nth(1)).isVisible();
        assertThat(page.getByText("$185.20").nth(1)).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).nth(1)).isVisible();
        assertThat(page.getByText("$164.98").nth(3)).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove product JBL Quantum")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your cart is empty"))).isVisible();
    }
}
