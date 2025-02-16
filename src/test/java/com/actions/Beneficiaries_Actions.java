package com.actions;

import java.util.List;
import java.util.Map;

import com.base.BaseAction;
import com.microsoft.playwright.Page;
import com.page.Beneficiaries_Page;
import com.page.CommonElements;
import com.utils.AllureUtils;

public class Beneficiaries_Actions {

	static int Person = 0;
	static int Entity = 0;

	public static void beneficiariesFlow(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Beneficiaries Information ");
		boolean notCompleted = BaseAction.isMenuCompleted(page, "Beneficiaries");
		BaseAction.assertTrueCondition(notCompleted == false, "section is not completed.");
		BaseAction.isTextPresent(page, Beneficiaries_Page.primaryBeneficiaries, "0");
		BaseAction.isTextPresent(page, Beneficiaries_Page.contingentBeneficiaries, "0");
		List<String> personOrEntity = BaseAction.split(rowData.get("Person or an Entity"));
		for (int i = 0; i < personOrEntity.size(); i++) {
			BaseAction.clickElement(page, Beneficiaries_Page.addBeneficiary);
			BaseAction.selectByValue(page, Beneficiaries_Page.beneficiaryType,
					BaseAction.split(rowData.get("Beneficiary Type")).get(i));
			BaseAction.selectByValue(page, Beneficiaries_Page.personOrEntity, personOrEntity.get(i));
			if (personOrEntity.get(i).equalsIgnoreCase("Person")) {
				BaseAction.fillInputField(page, Beneficiaries_Page.percentage,
						BaseAction.split(rowData.get("Person_Percentage")).get(Person));
				BaseAction.drSelection(page, Beneficiaries_Page.relationshipToOwner,
						BaseAction.split(rowData.get("Person_Relationship to Owner")).get(Person));
				BaseAction.typeInputField(page, Beneficiaries_Page.socialSecurityNumber,
						BaseAction.split(rowData.get("Person_Social Security Number")).get(Person));
				BaseAction.drSelection(page, CommonElements.prefix,
						BaseAction.split(rowData.get("Person_Prefix")).get(Person));
				BaseAction.fillInputField(page, CommonElements.firstName,
						BaseAction.split(rowData.get("Person_First Name")).get(Person));
				if(BaseAction.split(rowData.get("Person_Middle Name")).size()-1>=Person) {
				BaseAction.fillInputField(page, CommonElements.initial,
						BaseAction.split(rowData.get("Person_Middle Name")).get(Person));
				}
				BaseAction.fillInputField(page, CommonElements.lastName,
						BaseAction.split(rowData.get("Person_Last Name")).get(Person));
				if(BaseAction.split(rowData.get("Person_Suffix")).size()-1>=Person) {
				BaseAction.fillInputField(page, CommonElements.suffix,
						BaseAction.split(rowData.get("Person_Suffix")).get(Person));
				}
				BaseAction.datePicker(page, CommonElements.dateOfBirth,
						BaseAction.split(rowData.get("Person_Date of Birth")).get(Person));
				BaseAction.fillInputField(page, Beneficiaries_Page.street1,
						BaseAction.split(rowData.get("Person_Street Line 1")).get(Person));
				BaseAction.drSelectionContain(page, BaseAction.split(rowData.get("Person_City")).get(Person));
				page.waitForTimeout(2000);
				if(BaseAction.split(rowData.get("Person_Street Line 2")).size()-1>=Person) {
				BaseAction.fillInputField(page, Beneficiaries_Page.street2,
						BaseAction.split(rowData.get("Person_Street Line 2")).get(Person));
				}
//				page.waitForCondition(() -> {
//				    String value = LocatorFactory.getLocator(page, Beneficiaries_Page.City).inputValue();
//				    System.out.println("Current state value: " + value); // Debugging
//				    return !value.isEmpty();
//				}, new Page.WaitForConditionOptions().setTimeout(5000));
				BaseAction.isTextPresent(page, Beneficiaries_Page.state,
						BaseAction.split(rowData.get("Person_State")).get(Person));
				BaseAction.isTextPresent(page, Beneficiaries_Page.City,
						BaseAction.split(rowData.get("Person_City")).get(Person));
				BaseAction.isTextPresent(page, Beneficiaries_Page.zipCode,
						BaseAction.split(rowData.get("Person_Zip Code")).get(Person));

//				BaseAction.drSelection(page, Beneficiaries_Page.state,
//						BaseAction.split(rowData.get("Person_State")).get(Person));
//				BaseAction.typeInputField(page, Beneficiaries_Page.City,
//						BaseAction.split(rowData.get("Person_City")).get(Person));
//				BaseAction.typeInputField(page, Beneficiaries_Page.zipCode,
//						BaseAction.split(rowData.get("Person_Zip Code")).get(Person));

				BaseAction.typeInputField(page, CommonElements.phoneNumber,
						BaseAction.split(rowData.get("Person_Phone Number")).get(Person));
				BaseAction.fillInputField(page, CommonElements.emailAddress,
						BaseAction.split(rowData.get("Person_Email Address")).get(Person));
				BaseAction.clickElement(page, CommonElements.save);
				Person++;
			} else if (personOrEntity.get(i).equalsIgnoreCase("Entity")) {
				int ssn = 0;
				int taxID = 0;
				BaseAction.fillInputField(page, Beneficiaries_Page.percentage,
						BaseAction.split(rowData.get("Entity_Percentage")).get(Entity));
				BaseAction.fillInputField(page, Beneficiaries_Page.fullName,
						BaseAction.split(rowData.get("Entity_Full Name")).get(Entity));
				BaseAction.selectByValue(page, Beneficiaries_Page.identificationType,
						BaseAction.split(rowData.get("Entity_Identification Type")).get(Entity));
				if ("Social Security Number"
						.equalsIgnoreCase(BaseAction.split(rowData.get("Entity_Identification Type")).get(Entity))) {
					BaseAction.typeInputField(page, Beneficiaries_Page.socialSecurityNumber,
							BaseAction.split(rowData.get("Entity_Social Security Number")).get(ssn));
					ssn++;
				} else {
					BaseAction.typeInputField(page, Beneficiaries_Page.taxIDNumber,
							BaseAction.split(rowData.get("Entity_Tax ID Number / EIN")).get(taxID));
					taxID++;
				}
				BaseAction.datePicker(page, Beneficiaries_Page.formationDate,
						BaseAction.split(rowData.get("Entity_Formation Date")).get(Entity));
				BaseAction.fillInputField(page, Beneficiaries_Page.street1,
						BaseAction.split(rowData.get("Entity_Street Line 1")).get(Entity));
				BaseAction.drSelectionContain(page, BaseAction.split(rowData.get("Entity_City")).get(Entity));
				page.waitForTimeout(2000);
				if(BaseAction.split(rowData.get("Person_Suffix")).size()-1>=Entity) {
				BaseAction.fillInputField(page, Beneficiaries_Page.street2,
						BaseAction.split(rowData.get("Entity_Street Line 2")).get(Entity));
				}
				BaseAction.isTextPresent(page, Beneficiaries_Page.state,
						BaseAction.split(rowData.get("Entity_State")).get(Entity));
				BaseAction.isTextPresent(page, Beneficiaries_Page.City,
						BaseAction.split(rowData.get("Entity_City")).get(Entity));
				BaseAction.isTextPresent(page, Beneficiaries_Page.zipCode,
						BaseAction.split(rowData.get("Entity_Zip Code")).get(Entity));

//				BaseAction.drSelection(page, Beneficiaries_Page.state,
//						BaseAction.split(rowData.get("Entity_State")).get(Entity));
//				BaseAction.typeInputField(page, Beneficiaries_Page.City,
//						BaseAction.split(rowData.get("Entity_City")).get(Entity));
//				BaseAction.typeInputField(page, Beneficiaries_Page.zipCode,
//						BaseAction.split(rowData.get("Entity_Zip Code")).get(Entity));

				BaseAction.typeInputField(page, CommonElements.phoneNumber,
						BaseAction.split(rowData.get("Entity_Phone Number")).get(Entity));
				BaseAction.fillInputField(page, CommonElements.emailAddress,
						BaseAction.split(rowData.get("Entity_Email Address")).get(Entity));
				BaseAction.clickElement(page, CommonElements.save);
				Entity++;
			} else {
				System.err.println("Beneficiary a Person or an Entity? "
						+ BaseAction.split(rowData.get("Person or an Entity")).get(i));
			}
		}
		BaseAction.isTextPresent(page, Beneficiaries_Page.primaryBeneficiaries, "100");
		BaseAction.isTextPresent(page, Beneficiaries_Page.contingentBeneficiaries, "100");
		BaseAction.clickElement(page, CommonElements.next);
	}
}
