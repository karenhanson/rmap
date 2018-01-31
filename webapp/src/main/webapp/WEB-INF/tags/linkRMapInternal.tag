<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="my" uri="/WEB-INF/tld/rmapTagLibrary.tld" %>

<%@ attribute name="type" required="true" type="java.lang.String" description="type of link" %> 
<%@ attribute name="uri" required="true" type="java.lang.String" description="URI to form link from" %> 

<a href="<c:url value='/${type.toLowerCase()}s/${my:httpEncodeStr(uri)}'/>" title="View this ${type} in RMap">${uri}</a>