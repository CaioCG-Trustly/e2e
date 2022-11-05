-- Colocando a barra de search no widget
UPDATE pwmb.pwmb_paymentflow_merchants
SET ui_params='{"themeColor":{"colorSchemeMode":"light","bgLightColor":"#ffffff","bgDarkColor":"#25262c"},"dynamicWidget":{"name":"bank_tiles","fullWidth":false,"hideHeader":false,"text1":"ux.dynamicWidget.text1","text1Color":"#000000","text1DarkColor":"#ffffff","text2":"ux.dynamicWidget.text2","text2Color":"#6e6e6e","text2DarkColor":"#979797","text3Color":"#6e6e6e","text3DarkColor":"#979797","selectBankLabel":"ux.selectBank.title","showTextForMobileAndDesktop":false,"selectYourBankColor":"#dc4c34","selectYourBankDarkColor":"#dc4c34","showSearchBankElement":true,"widgetSearch":{"showElement":false,"placeholder":"ux.selectBank.typeYourBankName","showThousandsAvailableMessage":false,"showNoResultsMessage":false,"colorSchemeLightModeDefault":{"background":"#ededee","border":"#ededee","icon":"#676b75","text":"#676b75","availableNumber":"#676b75"},"colorSchemeLightModeActive":{"background":"#ffffff","border":"#265fc5","icon":"#024c51","cancelIcon":"#676b75","text":"#626262"},"colorSchemeDarkModeDefault":{"background":"#171e28","border":"#171e28","icon":"#d1d5db","text":"#d1d5db","availableNumber":"#d1d5db"},"colorSchemeDarkModeActive":{"background":"#4b5562","border":"#a3c8fa","icon":"#f6f7f9","cancelIcon":"#f6f7f9","text":"#f6f7f9"},"resultsColorSchemeLightModeDefault":{"background":"#ffffff","border":"#eff0f3","itemText":"#484949","mostPopularText":"#646463"},"resultsColorSchemeLightModeActive":{"itemBackground":"#eff0f2","itemText":"#494949"},"resultsColorSchemeDarkModeDefault":{"background":"#4b5563","border":"#374151","itemText":"#f6f7f9","mostPopularText":"#eff0f2"},"resultsColorSchemeDarkModeActive":{"itemBackground":"#6b7280","itemText":"#f6f7f9"},"noResultsColorSchemeLightMode":{"icon":"#555555","text":"#343434","buttonBorder":"#eff0f2","buttonText":"#646463"},"noResultsColorSchemeDarkMode":{"icon":"#f6f8fa","text":"#f6f8fa","buttonBorder":"#f6f8fa","buttonText":"#f6f8fa"},"noResultsDescription":"ux.widgetSearch.noResults.description"},"learnMore":{"showLearnMore":false,"showLearnMoreAsIconDesktop":false,"learnMoreColor":"#249dd6","learnMoreDarkColor":"#249dd6","learnMoreDescription":"ux.learnMore.description","learnMoreIconColor":"#249dd6","learnMoreIconDarkColor":"#249dd6"},"bankTilesConfiguration":{"numberOfHorizontalBanks":3,"numberOfVerticalBanks":2,"numberOfMobileBanks":"sameAsDesktop"},"bankButtonStyle":"squareEdges","otherBanksType":"allOtherBanks","otherBanksSubtitle":"ux.selectBank.thousandsAvailable","bankButtonBackgroundColor":"#ffffff","bankButtonBackgroundDarkColor":"#ffffff","bankButtonHighlightColor":"#5c9dce","bankButtonOutlineColor":"#e2e2e2","bankButtonOutlineHover":"#a8a8a8","bankButtonBorderWidth":"1px","actionButton":{"showButton":false,"text":"Continue","bgColor":"#dc4c34","bgDarkColor":"#dc4c34","fgColor":"#ffffff","fgDarkColor":"#ffffff"},"font":{"source":"none","size":"14px"},"topBanksList":"PayWithMyBank","footerType":"Default","footer":{"footerStyle":"BrandLogoInLightBackground","footerIcon":"none","footerText":"ux.dynamicWidget.footer","footerTextColor":"#959292","footerTextDarkColor":"#d8d8d8"},"manualAccountEntry":{"enabled":false,"text":"ux.dynamicWidget.manualAccountEntryText","textColor":"#0062a8","textDarkColor":"#ffffff","hasUnderlinedText":true}},"staticWidget":[{"countryCode":"FR","enableStaticWidget":true,"staticWidgetVersion":"twoButtons","staticLogosDesktopSrc":"{0}/start/assets/images/widget/fr/desktop.png","staticLogosMobileSrc":"{0}/start/assets/images/widget/fr/mobile.png","clickableLogos":false,"buttonsColor":"#3665f3","buttonsHoverColor":"#3665f3","buttonsStyle":"default"},{"countryCode":"IT","enableStaticWidget":true,"staticWidgetVersion":"twoButtons","staticLogosDesktopSrc":"{0}/start/assets/images/widget/it/desktop.png","staticLogosMobileSrc":"{0}/start/assets/images/widget/it/mobile.png","clickableLogos":false,"buttonsColor":"#3665f3","buttonsHoverColor":"#3665f3","buttonsStyle":"default"},{"countryCode":"ES","enableStaticWidget":true,"staticWidgetVersion":"twoButtons","staticLogosDesktopSrc":"{0}/start/assets/images/widget/es/desktop.png","staticLogosMobileSrc":"{0}/start/assets/images/widget/es/mobile.png","clickableLogos":false,"buttonsColor":"#3665f3","buttonsHoverColor":"#3665f3","buttonsStyle":"default"},{"countryCode":"GB","enableStaticWidget":true,"staticWidgetVersion":"twoButtons","staticLogosDesktopSrc":"{0}/start/assets/images/widget/gb/desktop.png","staticLogosMobileSrc":"{0}/start/assets/images/widget/gb/mobile.png","clickableLogos":false,"buttonsColor":"#3665f3","buttonsHoverColor":"#3665f3","buttonsStyle":"default"}],"page":{"closeButtonBehavior":"cancelAndClose","colorOption":"fiColor","fastSwitchLinks":false,"institutionBrand":{"type":"bankLogo"},"productBrandStyle":"Green","unchangeableTaxID":false},"header":{"style":"default","transactionDetails":{"position":"amountOnly"},"amount":{"amountStyle":"simpleText","amountTextColor":"#626262","amountTextDarkColor":"#d8d8d8","amountBackgroundColor":"#f6f6f6","amountBackgroundDarkColor":"#383a47"},"hideBackButton":false,"hideCloseButton":false},"button":{"style":"littleRounded","position":"fit","transformation":"none"},"primaryButton":{"bgColor":"#1173c7","bgDarkColor":"#1173c7","fgColor":"#ffffff","fgDarkColor":"#ffffff","sticky":false},"input":{"borderRadius":"littleRounded","appearance":"floating","colorOption":"fiColor"},"eCheck":{"validationsOnExit":false},"selectBank":{"startWithWidgetUx":false,"startWithWidgetUxInDesktop":false,"widgetBackButtonColor":"#000000","widgetBackButtonDarkColor":"#000000","bankList":{"type":"bankIcon","removeBankIconWithUrls":true,"smallIcons":false,"roundedIcons":false,"numberOfTopBanks":7,"fallbackAvatars":false,"hideSearch":false},"bankNotOnTheList":{"position":"list-item"},"searchInputHint":"ux.selectBank.thousandsAvailable","searchInputStyle":"searchStyleV4"},"login":{"title":"ux.login.signIntoYourBank","loginErrorMessageKey":"exception.invalidlogin","passwordVisibility":true,"showResetPasswordLink":false,"hideTermsAndConditions":false,"credentialsTooltips":{"enabled":false,"tip1":"ux.login.credentialsTooltips.tip1","tip2":"ux.login.credentialsTooltips.tip2","tip3":"ux.login.credentialsTooltips.tip3"},"welcomeSlider":{"enabled":true,"sliderType":"RightSideSlider","backgroundColor":"#0098d0","backgroundDarkColor":"#0098d0","titleColor":"#ffffff","titleDarkColor":"#ffffff","textColor":"#ffffff","textDarkColor":"#ffffff","backgroundGradient":false,"accentColor":"#ffffff","accentDarkColor":"#00ffff","title":"ux.welcomeSlider.title","line1Title":"ux.welcomeSlider.line1Title","line1":"ux.welcomeSlider.line1","line2Title":"ux.welcomeSlider.line2Title","line2":"ux.welcomeSlider.line2B","buttonText":"ux.welcomeSlider.ok"},"psd2":{"isLoginContext":false}},"appAuthentication":{"showButtonDesktop":false},"accounts":{"finishButtonKey":"ux.finishButtonLabel.payNowContinueWithdrawDeposit","hideAuthorizationText":false,"amountOnButton":false,"typeOfAccountSelection":"multipleAccountsUnselected","showAccountBalance":false,"deferredAmount":false,"showVANInfoPage":false,"hideMerchantNameFromInformationShare":false},"footer":{"hideMobile":false,"hideDesktop":false,"slider":true,"footerIcon":"none","footerStyle":"BrandCenter","shouldHideBorderTop":false},"googleRecaptcha":{"strategy":"V3withV2fallback","onLoginPage":{"enabled":false,"attemptCount":3}},"instantPayments":{"showRtpUi":true,"dynamicWidget":{"footerLabel":"ux.instantPayments.widget.footerLabel","paymentScheduleInformation":false},"lightbox":{"selectBankTooltip":"ux.instantPayments.lightbox.selectBankTooltip","selectBankTabButton":"ux.instantPayments.instantPayout","loginBankTitle":"ux.instantPayments.lightbox.loginBankTitle","loginBankTooltip":"ux.instantPayments.lightbox.loginBankTooltip","accountListDisclaimer":"ux.instantPayments.lightbox.accountListDisclaimer"}},"miscellaneous":{"handleHistory":false}}'
WHERE paymentflow_merchants_id=1001000128;