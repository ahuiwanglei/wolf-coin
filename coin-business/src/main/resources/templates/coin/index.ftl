<#include "/webroot.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>

    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>
    <link rel="shortcut icon" href="/favicon.ico">
    <link href="${webroot}/ligerUI/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <!-- 解决jQuery1.3.2和1.4.2的冲突  1.4.2 用Jquery  1.3.2 用 $ -->
    <script src="${webroot}/js/checkall.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${webroot}/css/button_defalut.css"/>
    <link href="${webroot}/css/css.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript">
        $(function () {

            loadData();

            $('#add').bind('click', function () {
                showCoin(-1);
            });


            $('#serbut').bind('click', function () {
                loadData();
            });
        });

        function loadData() {
            //window['g'] =
            $("#maingrid_user").ligerGrid({
                checkbox: false,
                columns: [{
                    display: '币种',
                    name: 'symbol'
                }, {
                    display: '手续费',
                    name: 'fees'
                }, {
                    display: '持仓数量',
                    name: 'position_count'
                }, {
                    display: '成本单价',
                    name: 'unit_amont'
                }, {
                    display: '买入阀值',
                    name: 'buy_threshold_rate'
                }, {
                    display: '卖出阀值',
                    name: 'sell_threshold_rate'
                }, {
                    display: '清仓阀值',
                    name: 'all_sell_threshold_rate'
                }, {
                    display: '状态',
                    name: 'status',
                    render: formatUserStatus
                }, {
                    display: '创建时间',
                    name: 'create_at'
                }, {
                    display: '操作',
                    name: 'id',
                    align: 'center',
                    render: formatOption
                }],
                dataAction: 'server',
                record: 'total',
                root: 'rows',
                pageSize: 10,
                pagesizeParmName: "rows",
                method: "POST",
                parms: {
                    searchkey: $('#searchkey').val(),
                    usertype: $('#s_user_type').val(),
                    userstatus: $("#s_user_state").val()
                },
                url: '${webroot}/web/coin/list/json'
            });
            $("#pageloading").hide();
        }


        function formatOption(rowdata, index, value) {
            var html = "";
            html += "<img src='${webroot}/images/037.gif'>&nbsp;";
            html += "<a style='cursor: pointer;' onclick='showCoin(" + value + ")'>编辑</a>&nbsp;&nbsp;";
            html += "<img src='${webroot}/images/010.gif'>&nbsp;";
            html += "<a style='cursor: pointer;' onclick='deleteUserInfo(" + value + ")'>删除</a>";
            return html;
        }


        //0 :监听正常交易  1:暂停交易  2,暂停监控
        function formatUserStatus(rowdata, index, value) {
            var STATUS = ["正常交易", "暂停交易", "暂停监控"];
            return STATUS[value];
        }

        function deleteUserInfo(id) {
            var msg = "";
            if (confirm(msg + "确认要删除该记录吗?")) {
                var urlStr = '${webroot}/web/coin/' + id + '/delete';
                $.ajax({
                    type: "POST",
                    url: urlStr,
                    dataType: "json",
                    error: function (request) {
                        alert('请求错误');
                    },
                    success: function (data) {
                        if (data.display == 0) {
                            alert(data.msg);
                        } else {
                            loadData();
                        }
                    }
                });
            }
        }

        function showCoin(id) {
            var url = "${webroot}/web/coin/show/" + id;
            $.ligerDialog.open({
                height: 600,
                width: 800,
                title: '币信息',
                url: url,
                showMax: false,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false
            });
        }
    </script>
</head>
<body style="background: #FFFFFF !important; padding: 0px;">
<div class="RightBox" style="width: 98%;">
    <div class="list_title_bg1" style="margin-right: 10px;">
        <div class="list_title_bg1_ico">监控配置&nbsp;<span>&gt;&gt;</span>&nbsp;监控币种</div>
    </div>
    <div class="serach" style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
        <span class="serimg">&nbsp;</span>

        <div class="serbod">
            <table>
                <tr align="center">

                    <td><span>币种：</span></td>
                    <td><input id="searchkey" type="text"/></td>
                    <td> &nbsp;&nbsp;
                        <input name="serbut" type="submit" id="serbut" value=" ">
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="list_info">
        <div class="bodtitle">
            <span class="tabimg"></span>
            <span class="tabfon">监控币</span>

            <div class="tabbut">
                <a id="add"  href="javascript:void(0)"></a>
            </div>
        </div>
            
        <div id="maingrid_user" style="margin-top:-10px; padding:0"></div>
    </div>
</div>
</body>
</html>