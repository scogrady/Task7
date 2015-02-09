<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.BuyFundBean"%>


<jsp:include page="template-top.jsp" />


<div class="col-xs-12 col-sm-12">

	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>Search on Flickr</h1>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Go!</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>

			<jsp:include page="error-list.jsp" />
			<jsp:include page="success.jsp" />

			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="reading_dog.jpg" alt="Please check the source">
						<div class="caption">
							<h3>Photo title</h3>
							<p>User id - description</p>
							<p>
								<a href="TransHistory.do" class="btn btn-primary" role="button">View Detail</a>
								 <a href="#" class="btn btn-default" role="button">Button</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="reading_dog.jpg" alt="Please check the source">
						<div class="caption">
							<h3>Photo title</h3>
							<p>User id - description</p>
							<p>
								<a href="TransHistory.do" class="btn btn-primary" role="button">View Detail</a>
								 <a href="#" class="btn btn-default" role="button">Button</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="reading_dog.jpg" alt="Please check the source">
						<div class="caption">
							<h3>Photo title</h3>
							<p>User id - description</p>
							<p>
								<a href="TransHistory.do" class="btn btn-primary" role="button">View Detail</a>
								 <a href="#" class="btn btn-default" role="button">Button</a>
							</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="reading_dog.jpg" alt="Please check the source">
						<div class="caption">
							<h3>Photo title</h3>
							<p>User id - description</p>
							<p>
								<a href="TransHistory.do" class="btn btn-primary" role="button">View Detail</a>
								 <a href="#" class="btn btn-default" role="button">Button</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="reading_dog.jpg" alt="Please check the source">
						<div class="caption">
							<h3>Photo title</h3>
							<p>User id - description</p>
							<p>
								<a href="TransHistory.do" class="btn btn-primary" role="button">View Detail</a>
								 <a href="#" class="btn btn-default" role="button">Button</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="reading_dog.jpg" alt="Please check the source">
						<div class="caption">
							<h3>Photo title</h3>
							<p>User id - description</p>
							<p>
								<a href="TransHistory.do" class="btn btn-primary" role="button">View Detail</a>
								 <a href="#" class="btn btn-default" role="button">Button</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div >
				<button class="btn btn-default" type="button">View more</button>
			</div>
		</div>
	</div>
</div>





<jsp:include page="template-bottom.jsp" />
