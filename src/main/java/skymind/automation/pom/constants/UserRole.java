package skymind.automation.pom.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    ADMIN("Administrator"),
    ASSET_MANAGEMENT("Asset Management"),
    BASIC_ACCESS("Basic Access"),
    INSIGHTS("Insights"),
    INTELLIGENT_MONITORING("Intelligent Monitoring"),
    LANE_MANAGEMENT("Lane Management"),
    LOGGER_MANAGEMENT("Logger Management"),
    PRODUCT_RELEASE("Product Release"),
    SHIPMENT_MANAGEMENT("Shipment Management"),
    SITE_MANAGEMENT("Site Management"),
    SKYMIND_ASSET_PAIRING("SkyMind Asset Pairing"),
    SKYSHIP_EVIDENCE_COLLECTION("SkyShip (Evidence Collection)"),
    SKYSHIP_TEMPERATURE_READOUT("SkyShip (Temperature Readout)");

    private final String roleName;

}
