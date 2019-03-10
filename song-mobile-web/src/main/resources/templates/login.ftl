<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <!-- ios系统的私有标签，它指定在web app状态下，ios设备中顶端的状态条的颜色 -->
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <!-- 设备浏览网页时对数字不启用电话功能 -->
    <meta content="telephone=no,email=no" name="format-detection" />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="css/common.css">
    <!-- 自适应样式单 -->
    <link rel="stylesheet" href="css/adaptive.css">
    <link rel="stylesheet" href="css/login.css">
<body class="zh_CN">
<div class="layout">
    <div class="nl-content">
        <div class="nl-logo-area" id="custom_display_1">
            <a href="javascript:void(0)"> <img id="logo-img"
                                               src="data:image/png;base64,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"
                                               data-src="/static/img/passport/nl-logo.png"
                                               onerror="this.src=&#39;/static/img/passport/nl-logo.png&#39;"
                                               width="60">
            </a>
        </div>

        <h1 class="nl-login-title" id="custom_display_256">
            <span id="message_LOGIN_TITLE">微信商城登录</span>
        </h1>
        <h2 class="nl-login-title lsrp-appname display-custom-hide"
            id="lsrp_appName"></h2>

        <div id="custom_display_2">
            <p class="nl-login-intro" id="message_LOGIN_LINKS">
                <a href="http://www.xiaomi.com/" target="_blank">酒查查手机</a>，<a
                        href="http://www.xiaomi.com/index.php" target="_blank">酒查查网</a>，<a
                        href="http://www.miui.com/" target="_blank">MIUI米柚</a>，<a
                        href="http://www.duokan.com/" target="_blank">多看阅读</a>，<a
                        href="http://www.miliao.com/" target="_blank">米聊</a>
            </p>
        </div>

        <div class="nl-phone-tip">
            <div id="message_LOGIN_PHONETIP">非大陆地区请在手机号码前面添加当地的国际代码前缀(如香港
                +852 台湾 +886)</div>
            <div id="select_country_code">帮助</div>
        </div>

        <div class="nl-frame-container">
            <div class="ng-form-area show-place" id="form-area">
                <form method="post" action="login" id="miniLogin"
                      onsubmit="return MiniLogin.validateForm();">

                    <div class="shake-area" id="shake_area" style="z-index: 30;">
                        <div class="enter-area display-custom-hide" id="revalidate_user">
                            <p class="revalidate-user-name" id="revalidate_user_name"></p>
                        </div>
                        <div class="enter-area" id="enter_user">
                            <input type="text" name="phone"
                                   class="enter-item first-enter-item"
                                   data-rule="(^[\w.\-]+@(?:[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*\.)+[A-Za-z]{2,6}$)|(^1\d{10}$)|(^\d{3,}$)|(^\++\d{2,}$)"
                                   id="miniLogin_username" autocomplete="off"
                                   placeholder="邮箱/手机号码"> <i
                                    class="placeholder hide" id="message_INPUT_IDENTITY">手机号码</i> <span
                                    class="error-tip"><em class="error-ico"></em><span
                                        class="error-msg"></span></span>
                        </div>
                        <div class="enter-area" style="z-index: 20;">
                            <input type="password" name="password"
                                   class="enter-item last-enter-item" id="miniLogin_pwd"
                                   autocomplete="off" data-rule="" placeholder="密码"> <i
                                    class="placeholder hide" id="message_INPUT_PASSWORD">密码</i> <span
                                    class="error-tip"><em class="error-ico"></em><span
                                        class="error-msg"></span></span>
                        </div>
                    </div>

                    <div class="enter-area img-code-area" id="img_code_area"
                         style="display: none;">
                        <input type="text" class="enter-item code-enter-item"
                               id="miniLogin_captCode" autocomplete="off" maxlength="12"
                               placeholder="验证码"> <img src="" class="code-img"
                                                       id="code_img"> <i class="placeholder hide"
                                                                         id="message_INPUT_CONFIRM">验证码</i> <span class="error-tip"><em
                                    class="error-ico"></em><span class="error-msg"
                                                                 id="code_error_tips"></span></span>
                    </div>
                    <#if error ??>
                        <div>
                            <span id="message_LOGIN_TOO_MUCH" style="color: red">${error}</span>
                        </div>
                    </#if>

                    <div class="miniLogin_forbidden" id="miniLogin_forbidden">
                        <div>
                            <span id="message_LOGIN_TOO_MUCH">您的操作频率过快，请稍后再试。</span>(<span
                                    id="retryCountdown"></span>)
                        </div>
                    </div>

                    <div class="miniLogin_forbidden">
                        <div>
                            <span id="message_LOGIN_FORZEN">此帐号已被冻结，暂时无法登录</span>
                        </div>
                    </div>
                    <input class="button orange" type="submit"
                           id="message_LOGIN_IMMEDIATELY" value="立即登录">

                    <div class="ng-foot clearfix">

                        <div style="display: none">
                            <div class="ng-cookie-area" id="cookie_area">
                                <input type="hidden" id="auto"><em class="checkbox"
                                                                   id="checkbox_item"></em> <span id="message_AUTOLOGIN_TWOWEEKS">两周内自动登录</span>
                            </div>
                        </div>

                        <div class="ng-link-area">
								<span> <a
                                            href="https://account.xiaomi.com/pass/sns/login/auth?appid=100284651&callback=http%3A%2F%2Fm.mi.com%2Fmshopapi%2Fv1%2Fauthorize%2Fsso_callback%3Ffollowup%3Dhttp%253A%252F%252Fm.mi.com%252Findex.html%2523ac%253Daccount%2526op%253Dindex%26sign%3DYjJhY2VjZWEwZDYzOTNhNmZhOTRjYmRmMDVlN2ZlZTJhZDFhOTViOA%2C%2C&sid=mi_eshopm"
                                            id="other_method_default">QQ联合登录</a><span> | </span>
								</span> <span id="custom_display_16"> <a
                                        href="javascript:void(0);" id="other_method">其他方式登录</a> <span>
										| </span>
								</span> <span id="custom_display_64"> <a
                                        href="https://account.xiaomi.com/pass/forgetPassword?callback=http%3A%2F%2Fm.mi.com%2Fmshopapi%2Fv1%2Fauthorize%2Fsso_callback%3Ffollowup%3Dhttp%253A%252F%252Fm.mi.com%252Findex.html%2523ac%253Daccount%2526op%253Dindex%26sign%3DYjJhY2VjZWEwZDYzOTNhNmZhOTRjYmRmMDVlN2ZlZTJhZDFhOTViOA%2C%2C&sid=mi_eshopm&_snsdefault=qq"
                                        id="message_FORGET_PASSWORD" target="_blank">忘记密码？</a>
								</span>
                            <div class="third-area hide" id="third_area"></div>
                        </div>

                    </div>

                    <span id="custom_display_128"> <a
                                href="localRegister"
                                class="button" id="message_REGISTER">注册帐号</a>
						</span> <span id="custom_display_8"> <a
                                href="https://account.xiaomi.com/pass/sns/login/auth?appid=222161937813280&callback=http%3A%2F%2Fm.mi.com%2Fmshopapi%2Fv1%2Fauthorize%2Fsso_callback%3Ffollowup%3Dhttp%253A%252F%252Fm.mi.com%252Findex.html%2523ac%253Daccount%2526op%253Dindex%26sign%3DYjJhY2VjZWEwZDYzOTNhNmZhOTRjYmRmMDVlN2ZlZTJhZDFhOTViOA%2C%2C&sid=mi_eshopm"
                                id="facebook_login_button" class="button facebook_area"> <i
                                    class="iconfacebook"></i>Facebook登录
						</a>
						</span> <a style="display: none" id="redirectLink" href="" target="_top"></a>
                    <a style="display: none" id="redirectTwoPhraseLoginLink" href=""></a>

                </form>
                <div class="qrlogin-trigger" id="qrlogin-trigger">二维码登录</div>
            </div>
        </div>

        <div class="web-info">
            <p class="web-info-content" id="web_info_content"></p>
        </div>
    </div>
</div>
<#include "common/bottom.ftl">
</body>
</html>