package quru.qa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    @BeforeEach
    void setUp() {
        open("https://demoqa.com/webtables");
    }

    @ValueSource(strings = {
            "Alden", "Cierra", "Kierra"
    })
    @ParameterizedTest(name = "Поиск по имени существующего сотрудника. Поисковой запрос с именем {0} не должен отдавать пустой список")
    @Tag("regression")
    void employeeSearchByFirstNameTest(String searchQuery) {

        $("#searchBox").setValue(searchQuery).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

    }

    @ValueSource(strings = {
            "Cantrell", "Vega", "Gentry"
    })
    @ParameterizedTest(name = "Поиск по фамилии существующего сотрудника. Поисковой запрос с фамилией {0} не должен отдавать пустой список")
    @Tag("regression")
    void employeeSearchByLastNameTest(String searchQuery) {

        $("#searchBox").setValue(searchQuery).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

    }

    @ValueSource(strings = {
            "45", "39", "29"
    })
    @ParameterizedTest(name = "Поиск по возрасту существующего сотрудника. Поисковой запрос с возрастом {0} не должен отдавать пустой список")
    @Tag("regression")
    void employeeSearchByAgeTest(String searchQuery) {

        $("#searchBox").setValue(searchQuery).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

    }

    @ValueSource(strings = {
            "alden@example.com", "cierra@example.com", "kierra@example.com"
    })
    @ParameterizedTest(name = "Поиск по email существующего сотрудника. Поисковой запрос с email {0} не должен отдавать пустой список")

    @Tag("regression")
    void employeeSearchByEmailTest(String searchQuery) {

        $("#searchBox").setValue(searchQuery).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

    }

    @ValueSource(strings = {
            "12000", "10000", "2000"
    })
    @ParameterizedTest(name = "Поиск по зарплате существующего сотрудника. Поисковой запрос с зарплатой {0} не должен отдавать пустой список")
    @Tag("regression")
    void employeeSearchBySalaryTest(String searchQuery) {

        $("#searchBox").setValue(searchQuery).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

    }

    @ValueSource(strings = {
            "Compliance", "Insurance", "Legal"
    })

    @ParameterizedTest(name = "Поиск по отделению существующего сотрудника. Поисковой запрос по отделению {0} не должен отдавать пустой список")

    @Tag("regression")
    @DisplayName("Поиск по отделению сотрудника")
    void employeeSearchByDepartmentTest(String searchQuery) {

        $("#searchBox").setValue(searchQuery).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

    }

    @ParameterizedTest(name = "Поиск по имени и фамилии с использованием @CsvSource")
    @CsvSource({
            "Alden, Cantrell",
            "Cierra, Vega",
            "Kierra, Gentry"
    })
    @Tag("regression")
    void employeeSearchByFullNameCsvSourceTest(String firstName, String lastName) {
        $("#searchBox").setValue(firstName).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

        $("#searchBox").setValue(lastName).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));
    }

    @ParameterizedTest(name = "Поиск по имени и фамилии с использованием @CsvFileSource")
    @CsvFileSource(resources = "/data/test_data.csv")
    @Tag("regression")
    void employeeSearchByFullNameCsvFileSourceTest(String firstName, String lastName) {

        $("#searchBox").setValue(firstName).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

        $("#searchBox").setValue(lastName).pressEnter();
        $$(".rt-tbody .rt-tr").shouldHave(sizeGreaterThan(0));

    }

    @Test
    @Tag("regression")
    @DisplayName("Поиск несуществующего сотдруника")
    void searchForAnNonExistentEmployeeTest() {

        $("#searchBox").setValue("Test").pressEnter();
        $(".rt-noData").shouldHave(text("No rows found"));

    }

}


