<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%--<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<%--<p>coderxz</p>--%>
				<%--获取每次的用户信息--%>
				<%--<p><security:authentication property="principal.username"/></p>--%>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>路径规划展示</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>
				<ul class="treeview-menu">
<%--					<li id="system-setting"><a--%>
<%--					&lt;%&ndash;<security:authorize access="hasRole('ROLE_ADMIN')">&ndash;%&gt;--%>
<%--						href="${pageContext.request.contextPath}/enemy/findAll.do"> <i--%>
<%--							class="fa fa-circle-o"></i> 经典轨迹显示--%>
<%--					</a>--%>
<%--				&lt;%&ndash;	</security:authorize>&ndash;%&gt;--%>
<%--					</li>--%>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/pages/test.html"> <i
							class="fa fa-circle-o"></i> 路径规划
					</a></li>
<%--					<li id="system-setting"><a--%>
<%--						href="${pageContext.request.contextPath}/permission/findAll.do">--%>
<%--							<i class="fa fa-circle-o"></i> 展示敌方enemy运行方向--%>
<%--					</a></li>--%>
<%--					<li id="system-setting"><a--%>
<%--						href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i--%>
<%--							class="fa fa-circle-o"></i> 我方受到威胁的点--%>
<%--					</a></li>--%>
				</ul></li>
<%--			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>--%>
<%--					<span>敌友军展示</span> <span class="pull-right-container"> <i--%>
<%--						class="fa fa-angle-left pull-right"></i>--%>
<%--				</span>--%>
<%--			</a>--%>
<%--				<ul class="treeview-menu">--%>

<%--					<li id="system-setting"><a--%>
<%--						href="${pageContext.request.contextPath}/enemy/findAll.do">--%>
<%--							<i class="fa fa-circle-o"></i> 敌军展示--%>
<%--					</a></li>--%>
<%--					<li id="system-setting"><a--%>
<%--						href="${pageContext.request.contextPath}/orders/findAll.do?page=1&size=4"> <i--%>
<%--							class="fa fa-circle-o"></i> 友军展示--%>
<%--					</a></li>--%>

<%--				</ul></li>--%>

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>