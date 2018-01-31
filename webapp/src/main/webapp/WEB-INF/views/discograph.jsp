<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/rmapTagLibrary.tld" %>
<%@ taglib prefix="tl" tagdir="/WEB-INF/tags"%>

<c:set var="graph" value="${GRAPH}"/>
<c:set var="nodes" value="${GRAPH.getNodes().values()}"/>
<c:set var="edges" value="${GRAPH.getEdges()}"/>
<c:set var="resourceUri" value="${RESOURCEURI}"/>
<c:set var="nodeTypes" value="${GRAPH.getNodeTypes().values()}"/>

<c:if test="${graph==null}">
	<p class="notice">This DiSCO is too large to display as a visualization. Click the "table view" link above to view the data in table. You can navigate the DiSCO's contents from there.</p>
</c:if>

<c:if test="${graph!=null}">
	<style>
	<c:forEach var="nodeType" items="${nodeTypes}" varStatus="loop">
		<c:if test="${!nodeType.getShape().equals('image')}">
			.legend${nodeType.getName()} {
				background: ${nodeType.getColor()};
				}
		</c:if>
	</c:forEach>
	</style>	
	<%@include file="/includes/js/nodesedges.js" %>   
	
	<c:if test="${VIEWMODE.equals('standard')}">
		<tl:graphStandard nodeTypes="${nodeTypes}" visualuri="/discos/${my:httpEncodeStr(resourceUri)}/visual"/>
	<p id="textnote">This visualization is interactive. You can move things around, click/tap on an item to see more information, or double-click/tap on an item to recenter the view.</p>
	</c:if>
	<c:if test="${VIEWMODE.equals('visual')}">
		<tl:graphLarge nodeTypes="${nodeTypes}" summaryview="/discos/${my:httpEncodeStr(resourceUri)}"/>
	</c:if>
	<c:if test="${VIEWMODE.equals('widget')}">
		<tl:graphWidget nodeTypes="${nodeTypes}" rmapviewuri="/discos/${my:httpEncodeStr(resourceUri)}"/>
	</c:if>
</c:if>