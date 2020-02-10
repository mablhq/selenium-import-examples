exports.config = {
  framework: 'jasmine',
  seleniumAddress: 'http://localhost:4444/wd/hub',
  specs: ['src/test/spec.js'],
  webDriverProxy: 'http://localhost:8889',
}
