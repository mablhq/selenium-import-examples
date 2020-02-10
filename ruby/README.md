# Ruby

## Running the example
1. Make sure that the `selenium-webdriver` gem is installed:
```
$ gem install selenium-webdriver
```
2. Start a chromedriver process in a separate terminal:
```
$ chromedriver --port=9515
```
3. Begin an import session using the mabl CLI in a separate terminal:
```
$ mabl tests import --name "Ruby Import Example"
```
4. Run the test in this directory:
```
$ ruby test.rb
```
5. View/run/save the imported test in the terminal where you started the import:

## Adapting your own Ruby tests for import into mabl

In order to import your Selenium tests it is necessary to:
1. Use a remote web driver process (e.g. start `chromedriver` or use Selenium Grid).
1. Modify your test to use a Selenium proxy at `localhost:8889` (see [test.rb](test.rb#L3-L5)).
1. Start an import session in the mabl CLI (`mabl tests import...`).
1. Run your test
1. Save your imported test using the mabl CLI.
