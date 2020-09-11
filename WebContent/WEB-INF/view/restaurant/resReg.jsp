<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
    <div>
        <form action="/restaurant/regProc" method="post">
            <div><input type="text" name="nm" placeholder="가게명"></div>
            <div><input type="text" name="addr" placeholder="주소"><button>좌표가져오기</button></div>
            <input type="hidden1" name="lat" value="0">
            <input type="hidden2" name="lng" value="0">
            <div>
                <span>카테고리:</span>
                <select name="cd_category" id="">
                    <c:foreach items="${categoryList}" var="item">
                        <option value="${item.cd}">${item.val}</option>
                    </c:foreach>
                </select>
            </div>
        </form>
    </div>
</div>