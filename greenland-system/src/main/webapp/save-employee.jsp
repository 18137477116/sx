<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>办公系统 - 基础表格</title>
    <meta name="keywords" content="办公系统">
    <meta name="description" content="办公系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    	<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
	<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
      <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>员工管理<small>>添加信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal">
                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-3">
                                    <input id="employee-name" name="name" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">用户名</label>
                                <div class="col-sm-3">
                                    <input id="employee-username" name="name" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-3">
                                    <input id="employee-password" name="name" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">性别</label>
                                <div class="col-sm-3">
                                    <select id="employee-sex" name="level" class="selectpicker form-control">
										<option>男</option>
										<option>女</option>
									</select>
                                </div>                     
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-3">
                                    <input id="employee-credit-number" name="name" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">联系电话</label>
                                <div class="col-sm-3">
                                    <input id="employee-phone" name="name" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                              <label class="col-sm-2 control-label">部门</label>
                                <div class="col-sm-3">
                                    <select id="employee-dept" name="level" class="selectpicker form-control">
                                        <option value="2">项目管理</option>
                                        <option value="3">日常办公</option>
                                        <option value="4">消息管理</option>
                                        <option value="28">客户信息管理</option>
                                        <option value="34">系统管理</option>
                                        <option value="35">对标管理</option>
                                        <option value="36">个人信息</option>
									</select>
                                </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-offset-1 control-label">入职时间</label>
                                <div class="col-sm-3">
                                    <!--时间控件的id都不能改-->
                                    <input id="employee-hiredate" type="date" name="start" id="start" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                              <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>
                                <div class="col-sm-3">
                                    <input id="employee-age" name="name" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">角色</label>
                                <div class="col-sm-3">
                                    <select id="employee-role" name="level" class="selectpicker form-control">
										<option value="5">Customer</option>
										<option value="6">VIP</option>
										<option value="7">VVIP</option>
									</select>
                                </div>                     
                            </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea id="employee-remark" name="remark" class="form-control"></textarea>
                                </div>
                                
                            </div>
                        </div>
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>

                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button id="employee-add-btn" type="button" class="btn btn-primary"><i class="fa fa-save"></i> 保存内容</button>
                                </div>
                                <div class="col-sm-3">
                                	<a href="list-employee.jsp" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
                                	</div>
                            </div>
                       </div>
                       </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

 
    
    
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="js/plugins/select/bootstrap-select.min.js"></script>
	<script src="js/plugins/layer/laydate/laydate.js"></script>
   <script>
	$(document).ready(function() {


        $("#employee-add-btn").click(function () {
            addEmployee();
            return false;
        })

		function addEmployee(){
		    let d = {
                ename:$("#employee-name").val(),
                esex:$("#employee-sex").val(),
                eage:$("#employee-age").val(),
                telephone:$("#employee-phone").val(),
                hiredate:$("#employee-hiredate").val(),
                pnum:$("#employee-credit-number").val(),
                username:$("#employee-username").val(),
                password:$("#employee-password").val(),
                remark:$("#employee-remark").val(),
                dfk:$("#employee-dept").val(),
                roleId:$("#employee-role").val(),
            };
            console.log("/api/employee/add   data -> ",d)
            $.ajax({
                type: "POST",
                url: "/api/employee/add",
                dataType: "json",
                data: d,
                success: function (res) {
                    if(res.code==0){
                        alert("添加成功");
                        $("#employee-name").val("");
                        $("#employee-sex").val("");
                        $("#employee-age").val("");
                        $("#employee-phone").val("");
                        $("#employee-hiredate").val("");
                        $("#employee-credit-number").val("");
                        $("#employee-username").val("");
                        $("#employee-password").val("");
                        $("#employee-remark").val("");
                        $("#employee-dept").val("");
                        $("#employee-role").val("");
                    }
                },
                error: function (err) {
                    console.log("/api/sources/add request error!!!")
                }
            });
        }


	});
   </script>
   <!-- 修复日期控件长度-->
   <link href="css/customer.css" rel="stylesheet">
</body>


</html>
    