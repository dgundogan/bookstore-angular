import { StoreFrontPage } from './app.po';

describe('store-front App', () => {
  let page: StoreFrontPage;

  beforeEach(() => {
    page = new StoreFrontPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
