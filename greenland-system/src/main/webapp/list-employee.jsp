<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>绿地中央广场综合物业办公系统 - 基础表格</title>
    <meta name="keywords" content="综合办公系统">
    <meta name="description" content="综合办公系统">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="css/plugins/select/bootstrap-select.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper2 wrapper-content2 animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="row">
                <div class="col-sm-3 col-sm-offset-2 text-right">
                    <h3><small>搜索条件:</small></h3>
                </div>

                <div class="col-sm-3">
                    <div class="input-group">
                        <input id="employee-search-box" type="text" placeholder="请输入关键词" class="input-sm form-control">
                        <span class="input-group-btn">
                                        <button type="button" id="employee-search-btn" class="btn btn-sm btn-primary"><i class="fa fa-search"></i>搜索</button>
                         </span>
                    </div>
                </div>
                <div class="col-sm-2 text-right">
                    <a href="save-employee.jsp" class="btn btn-sm btn-primary" type="button"><i class="fa fa-plus-circle"></i> 添加员工</a>
                </div>
            </div>
            <div class="hr-line-dashed2"></div>
            <div class="table-responsive">
                <table class="table table-striped list-table">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>联系电话</th>
                        <th>身份证</th>
                        <th>入职时间</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody id="employ-table-body">
                    <tr>
                        <td><input type="checkbox" checked=""></td>
                        <td>1</td>
                        <td>李晓明</td>
                        <td>初级开发工程师</td>
                        <td>男</td>
                        <td>24</td>
                        <td>13288888888</td>
                        <td>2015-01-03</td>
                        <td class="text-right">
                            <a href="show-employee.jsp" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i>编辑</a>
                            <button class="btn btn-danger btn-xs btndel"><i class="fa fa-close"></i>删除</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="row">
                <div class="col-sm-5">
                    <button class="btn btn-sm btn-primary" type="button"><i class="fa fa-check-square-o"></i> 全选</button>
                    <button class="btn btn-sm btn-primary" type="button"><i class="fa fa-square-o"></i> 反选</button>
                    <button class="btn btn-sm btn-primary" type="button"><i class="fa fa-times-circle-o"></i> 删除</button>
                    <button id="demo1" class="btn btn-sm btn-primary" type="button"><i class="fa fa-table"></i> 导出Excel</button>
                </div>
                <div class="col-sm-7 text-right">
                    <span id="employee-page-text">共有50页,当前是第3页</span>
                    <a href=''>首页</a>
                    <a href=''>上一页</a>
                    <a href=''>下一页</a>
                    <a href=''>尾页</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<script src="js/plugins/select/bootstrap-select.min.js"></script>
<script src="js/plugins/sweetalert/sweetalert.min.js"></script>

<script>


    $(document).ready(function () {
        // 设置按钮的样式
        $('.selectpicker').selectpicker('setStyle', 'btn-white').selectpicker('setStyle', 'btn-sm');

        //点击删除
        $('.btndel').click(function (e) {
            console.log(e)
            swal({
                title: "您确定要删除这条信息吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                closeOnConfirm: false
            }, function () {//此函数是点击删除执行的函数
                let eid = 7
                $.ajax({
                    type: "GET",
                    contentType: "application/json;charset=UTF-8",
                    url: "/api/employee/delete/{"+eid+"}",
                    dataType: "json",
                    success: function (res) {
                        alert("删除成功")
                        loadEmployee()
                    },
                    error: function (err) {
                        alert("删除出错")
                    }
                })
                swal("删除成功！", "您已经永久删除了这条信息。", "success")
            })
        })


        var totalPage = 0
        var currentPage = 0
        var pageSize = 10
        var key = $("#employee-search-box").val()

        loadEmployee();

        $("#employee-search-btn").click(function (e) {
            key = $("#employee-search-box").val();
            loadEmployee()
        })



        $(".btndel").click(function (e) {
            console.log("event: ",e)
        })


//----------------------------------------------------------------
        function loadEmployee() {
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=UTF-8",
                url: "/api/employee/list",
                dataType: "json",
                data: {
                    current: currentPage,
                    size: pageSize,
                    key:key
                },
                success: function (res) {
                    $("#employ-table-body").html("")
                    res.data.records.forEach((itm, idx) => {
                        $("#employ-table-body").append(element(itm, idx));
                })
                    totalPage = res.data.total;
                    currentPage = res.data.current;
                    $("#employee-page-text").text("共有" + totalPage + "页,当前是第" + currentPage + "页")
                },
                error: function (err) {
                    console.log("employee list request error!!!")
                }
            });
        }

        function element(itm, idx) {
            let $e = $('             <tr data-eid='+itm.eid+'>\n' +
                '                        <td><input type="checkbox" checked="false"></td>\n' +
                '                        <td>' + (idx + 1) + '</td>\n' +
                '                        <td>' + itm.ename + '</td>\n' +
                '                        <td>' + itm.esex + '</td>\n' +
                '                        <td>' + itm.eage + '</td>\n' +
                '                        <td>' + itm.telephone + '</td>\n' +
                '                        <td>' + itm.hiredate.join("-")+ '</td>\n' +
                '                        <td class="text-right">\n' +
                '                            <a href="show-employee.jsp" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i>编辑</a>\n' +
                '                            <button class="btn btn-danger btn-xs btndel" onclick="removeEmployee(' + itm.eid + ')"><i class="fa fa-close"></i>删除</button>\n' +
                '                        </td>\n' +
                '                    </tr>')
            return $e;
        }




    });

    function  removeEmployee(id) {
        $.ajax({
            type: "POST",
            url: "/api/employee/delete",
            dataType: "json",
            data:{
                id:id
            },
            success: function (res) {
                console.log("delete employee res",res)
                if(res.code==0){
                    alert("删除员工成功");
                    $("#employ-table-body [data-eid="+id+"]").remove();
                }else{
                    alert("删除员工失败");
                }
            },
            error: function (err) {
                alert("删除员工失败, 如果绑定角色先结绑角色");
            }
        });
    }
</script>

</body>


</html>
    