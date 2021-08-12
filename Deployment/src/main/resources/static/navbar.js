
Vue.component('mynavbar', {
  template:'<!-- HEADER DESKTOP-->'+
 '<header class="header-desktop3 d-none d-lg-block">'+
      '<div class="section__content section__content--p35">'+
          '<div class="header3-wrap">'+
              '<div class="header__logo">'+
                  '<a href="#">'+
                      '<img src="../images/logo-white.png" alt="نظام الموارد البشرية" />'+
                  '</a>'+
              '</div>'+
              '<div class="header__navbar">'+
                  '<ul class="list-unstyled">'+
                      '<li class="has-sub">'+
                          '<a href="#">'+
                              '<i class="fas fa-credit-card"></i>&nbsp;'+
                              'النظام المالي'+
                             '<span class="bot-line"></span>'+
                          '</a>'+
                          '<ul class="header3-sub-list list-unstyled">'+
                              '<li>'+
                                  '<a href="index.html">Dashboard 1</a>'+
                              '</li>'+
                          '</ul>'+
                      '</li>'+
                      '<li>'+
                          '<a href="#">'+
                              '<i class="fas fa-users"></i>&nbsp;'+
                              '<span class="bot-line"></span>الموارد البشرية</a>'+
                      '</li>'+
                      '<li class="has-sub">'+
                          '<a href="#">'+
                              '<i class="fas fa-tasks"></i>&nbsp;'+
                              '<span class="bot-line"></span>إدارة المشاريع</a>'+
                              '<ul class="header3-sub-list list-unstyled">'+
                                  '<li>'+
                                      '<a href="./project/projects.html"> المشاريع </a>'+
                                  '</li>'+
                                  '<li>'+
                                      '<a href="./project/project_form.html">إضافة مشروع</a>'+
                                  '</li>'+
                                  '<li>'+
                                      '<a href="./project/strategies_form.html">إضافة هدف إستراتيجي</a>'+
                                  '</li>'+
                              '</ul>'+
                      '</li>'+
                  '</ul>'+
              '</div>'+
              '<div class="header__tool">'+
              '<div class="account-wrap" style="margin-left:-40px;">'+
              '<div class="account-item account-item--style2 clearfix js-item-menu">'+
                  '<div class="image" style="float:right;">'+
                      '<img src="../images/user.png" alt="John Doe" />'+
                  '</div>'+
                  '<div class="content" style="margin-right:45px;">'+
                      '<a class="js-acc-btn" href="#">Shahad Alkathiri</a>'+
                  '</div>'+
                  '<div class="account-dropdown js-dropdown" style="min-width:230px;">'+
                      '<div class="info clearfix">'+
                         '<div class="image">'+
                              '<a href="#">'+
                                  '<img src="../images/user.png" alt="John Doe" />'+
                              '</a>'+
                          '</div>'+
                          '<div class="content">'+
                              '<h5 class="name">'+
                                  '<a href="#">john doe</a>'+
                              '</h5>'+
                              '<span class="email">johndoe@example.com</span>'+
                          '</div>'+
                      '</div>'+
                      '<div class="account-dropdown__body">'+
                          '<div class="account-dropdown__item">'+
                              '<a href="#">'+
                                  '<i class="zmdi zmdi-account"></i>الحساب</a>'+
                          '</div>'+
                          '<div class="account-dropdown__item">'+
                              '<a href="#">'+
                                  '<i class="zmdi zmdi-settings"></i>الإعدادات</a>'+
                          '</div>'+
                      '</div>'+
                      '<div class="account-dropdown__footer">'+
                          '<a href="#">'+
                              '<i class="zmdi zmdi-power"></i>تسجيل الخروج</a>'+
                      '</div>'+
                  '</div>'+
              '</div>'+
          '</div>'+
                  '<div class="header-button-item has-noti js-item-menu" style="margin-left:40px;">'+
                      '<i class="zmdi zmdi-notifications"></i>'+
                      '<div class="notifi-dropdown notifi-dropdown--no-bor js-dropdown">'+
                          '<div class="notifi__title">'+
                              '<p>لديك ١ من الإشعارات </p>'+
                          '</div>'+
                          '<div class="notifi__item">'+
                              '<div class="bg-c1 img-cir img-40">'+
                                  '<i class="zmdi zmdi-email-open"></i>'+
                              '</div>'+
                              '<div class="content">'+
                                  '<p> دعوة لاجتماع </p>'+
                                  '<span class="date">April 12, 2018 06:50</span>'+
                              '</div>'+
                          '</div>'+
                          '<div class="notifi__footer">'+
                              '<a href="#">جميع الإشعارات  </a>'+
                          '</div>'+
                      '</div>'+
                  '</div>'+
              '</div>'+
          '</div>'+
      '</div>'+
  '</header>'+
  '<!-- END HEADER DESKTOP-->'});

new Vue({
  el:'#navbar',
})