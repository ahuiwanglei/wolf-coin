<#include "/webroot.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${webroot}/css/css.css" rel="stylesheet" type="text/css"/>
    <link href="${webroot}/css/main.css" rel="stylesheet" type="text/css"/>
    <link href="${webroot}/ligerUI/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <!-- 解决jQuery1.3.2和1.4.2的冲突  1.4.2 用Jquery  1.3.2 用 $ -->
    <script src="${webroot}/ligerUI/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $('#cancelBtn').bind('click', function () {
                parent.$.ligerDialog.close();
                parent.$(".l-dialog,.l-window-mask").css("display", "none");
            });
            $('#createBtn').bind('click', function () {
                var coin_symbol = $('#coin_symbol').val();
                var coin_fees = $('#coin_fees').val();
                var coin_buy_threshold_rate = $("#coin_buy_threshold_rate").val();
                var coin_sell_threshold_rate = $("#coin_sell_threshold_rate").val();
                var coin_all_sell_threshold_rate = $("#coin_all_sell_threshold_rate").val();
                if (coin_symbol.replace(/\s/img, "") == "") {
                    alert("币代码不能为空");
                    return;
                }
                if (coin_fees.replace(/\s/img, "") == "") {
                    alert("交易费不能为空");
                    return;
                }
                if (coin_buy_threshold_rate.replace(/\s/img, "") == "") {
                    alert("购买阀值不能为空");
                    return;
                }
                if (coin_sell_threshold_rate.replace(/\s/img, "") == "") {
                    alert("卖出阀值不能为空");
                    return;
                }
                if (coin_all_sell_threshold_rate.replace(/\s/img, "") == "") {
                    alert("清仓阀值不能为空");
                    return;
                }

                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: '${webroot}/web/coin/save',
                    data: {
                        'coin_symbol': coin_symbol,
                        'coin_fees': coin_fees,
                        'coin_buy_threshold_rate': coin_buy_threshold_rate,
                        'coin_sell_threshold_rate': coin_sell_threshold_rate,
                        'coin_all_sell_threshold_rate': coin_all_sell_threshold_rate,
                        'coin_status': $("#coin_status").val(),
                        'coin_id': $("#coinid").val()
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        $.messager.alert('Info', XMLHttpRequest.responseText);
                        parent.$.ligerDialog.close();
                        parent.$(".l-dialog,.l-window-mask").css("display", "none");
                    },
                    success: function (data) {
                        if (data.display == 0) {
                            alert(data.msg);
                        } else {
                            parent.$.ligerDialog.close();
                            parent.$(".l-dialog,.l-window-mask").css("display", "none");
                            parent.window.loadData();
                        }

                    }
                });

            });
        });
    </script>
</head>
<body style="background: #FFFFFF !important; padding: 0px;">
<div class="RightBox">
    <div class="show" style="margin-bottom:0PX;"><span class="co5">温馨提示：<#if coin?? >币代码不能修改<#else>币代码不能修改，请核对后填写</#if></span></div>
    <div class="add_info_cont">
        <form id="serachFrom" name="serachFrom" method="post" action="" style="margin:0px">
            <table class="add_info_table" width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th>币代码：</th>
                    <td><input name="textfield3" type="text"  <#if coin?? >disabled</#if> class="textinput1" id="coin_symbol"
                               value="<#if coin?? >${coin.symbol}</#if>"/></td>
                </tr>
                <tr>
                    <th>手续费：</th>
                    <td><input name="textfield3" type="text" class="textinput1" id="coin_fees"
                               value="<#if coin?? >${coin.fees}</#if>"/></td>
                </tr>
                <tr>
                    <th>买入阀值：</th>
                    <td><input name="textfield3" type="text" class="textinput1" id="coin_buy_threshold_rate"
                               value="<#if coin?? >${coin.buy_threshold_rate}</#if>"/></td>
                </tr>
                <tr>
                    <th>卖出阀值：</th>
                    <td><input name="textfield3" type="text" class="textinput1" id="coin_sell_threshold_rate"
                               value="<#if coin?? >${coin.sell_threshold_rate}</#if>"/></td>
                </tr>
                <tr>
                    <th>清仓阀值：</th>
                    <td><input name="textfield3" type="text" class="textinput1" id="coin_all_sell_threshold_rate"
                               value="<#if coin?? >${coin.all_sell_threshold_rate}</#if>"/></td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>
                        <select id="coin_status">
                            <option value="0" <#if coin?? && coin.status == 0 >selected</#if>>正常交易</option>
                            <option  value="1" <#if coin?? && coin.status == 1 >selected</#if>>暂停交易</option>
                            <option value="2" <#if coin?? && coin.status == 2 >selected</#if>>暂停监听</option>
                        </select>
                    </td>
                </tr>
            </table>
            <div class="add_info_btn">
                <input type="hidden" id="coinid" value="<#if coin?? >${coin.id}</#if>"/>
                <input type="button" value="保&nbsp;存" id="createBtn" class="btn_bg"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" class="btn_bg" id="cancelBtn" value="取&nbsp;消"/>
            </div>
        </form>
    </div>

</div>
</body>
</html>
