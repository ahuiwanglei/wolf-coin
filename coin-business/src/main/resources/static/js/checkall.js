/**
 * Created by WANG427 on 2016/3/14.
 */
var checkedIds = [];
function f_onCheckRow(checked, data){
    if (checked) addCheckedCustomer(data.id);
    else removeCheckedCustomer(data. id);
}

function f_onCheckAllRow(checked)
{
    for (var rowid in this.records)
    {
        if(checked)
            addCheckedCustomer(this.records[rowid]['id']);
        else
            removeCheckedCustomer(this.records[rowid]['id']);
    }
}


function removeCheckedCustomer(id)
{
    var i = findCheckedCustomer(id);
    if(i==-1) return;
    checkedIds.splice(i,1);
}
function addCheckedCustomer(id)
{
    if(findCheckedCustomer(id) == -1)
        checkedIds.push(id);
}

function findCheckedCustomer(id) {
    for (var i = 0; i < checkedIds.length; i++) {
        if (checkedIds[i] == id) return i;
    }
    return -1;
}

function deleteBatchData(urlStr){
    if (confirm("确认要删除选中的数据吗?")) {
        $.ajax({
            type: "POST",
            url: urlStr,
            dataType: "json",
            data:{
                ids : checkedIds.join("-")
            },
            error: function (request) {
                alert('请求错误');
            },
            success: function (data) {
                if (data.display == 0) {
                    alert(data.msg);
                } else {
                    loadData();
                    //alert("成功");
                }
            }
        });
    }
}