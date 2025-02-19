# Protractor

## Setup
1. Install node
2. Install `webdriver-manager`:
```
$ npm i -g webdriver-manager
$ webdriver-manager update
```

## Running the example
1. Start Protractor's `webdriver-manager` in a separate terminal:
```
$ webdriver-manager start
```
2. Begin an import session using the mabl CLI in a separate terminal:
```
$ mabl tests import --name "Protractor Import Example"
```
3. Run the tests in this directory:
```
$ npm test
```
4. View/run/save the imported test in the terminal where you started the import:

## Adapting your own Protractor tests for import into mabl

Only a single configuration change is required to make your Protractor tests compatible with mabl import.
Add the following setting to your Protractor configuration file:
```
webDriverProxy: 'http://localhost:8889',
```

Then run your tests as you normally would with this configuration change applied:
1. Start the Protractor webdriver manager
1. Verify that your Protractor configuration file has `webDriverProxy: 'http://localhost:8889'` set
1. Start an import session in the mabl CLI (`mabl tests import...`).
1. Run your test (e.g. `npm test`).
1. Save your imported test using the mabl CLI.
