# Java

## Running the example
1. Start a chromedriver process in a separate terminal
```
$ chromedriver --port=9515
```
2. Begin an import session using the mabl CLI in a separate terminal:
```
$ mabl tests import --name "Java Import Example"
```
3. Run the unit tests in this directory:
```
$ ./gradlew test --rerun-tasks
```
4. View/run/save the imported test in the terminal where you started the import:

## Adapting your own Java Selenium tests for import into mabl

In order to import your Selenium tests it is necessary to:
1. Use a remote web driver process (e.g. start `chromedriver` or use Selenium Grid).
1. Modify your test to use a Selenium proxy at `localhost:8889` (see [SeleniumTestWithProxySupport](src/test/java/com/mabl/example/selenium/SeleniumTestWithProxySupport.java)).
1. Start an import session in the mabl CLI (`mabl tests import...`).
1. Run your test (e.g. `./gradlew test`, `mvn test`, etc.).
1. Save your imported test using the mabl CLI.
