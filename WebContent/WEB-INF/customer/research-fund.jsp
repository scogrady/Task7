<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="page-header">
					<h3>
						The most popular chooses.
					</h3>
				</div>
				<div class="row">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<img data-src="holder.js/300x300" alt="...">
							<div class="caption">
								<h3>Fund A - TICKER</h3>
								<p>Bigger risk, bigger reward. </p>
	        					<p><a href="#" class="btn btn-primary" role="button">View More</a> <a href="#" class="btn btn-default" role="button">Compare</a></p>
	      					</div>
	    				</div>
	  				</div>

					<div class="col-sm-6 col-md-4">
				    	<div class="thumbnail">
				      	<img data-src="holder.js/300x300" alt="...">
				    		<div class="caption">
				        		<h3>Fund B - TICKER</h3>
				        		<p>-.-</p>
				        		<p><a href="#" class="btn btn-primary" role="button">View More</a> <a href="#" class="btn btn-default" role="button">Compare</a></p>
				      		</div>
				    	</div>
				  	</div>
				
				  	<div class="col-sm-6 col-md-4">
				    	<div class="thumbnail">
				      	<img data-src="holder.js/300x300" alt="...">
				      		<div class="caption">
				        		<h3>Fund C - TICKER</h3>
				        		<p> Safety is the most important thing.</p>
				        		<p><a href="#" class="btn btn-primary" role="button">View More</a> <a href="#" class="btn btn-default" role="button">Compare</a></p>
				      		</div>
				    	</div>
				  	</div>
				</div>

				



				<div class="row" >
					<div class="col-sm-6 col-md-4">
						<h3>
							Mutual Funds.
						</h3>


						<ol>
							<li>
								<h4>
									fundA - TICKER
								</h4>
								<p>
									Always increases.
								</p>
								<p>
									<a class="btn" href="#">View More »</a>
								</p>
							</li>
							<li>
								<h4>
									fundB - TICKER
								</h4>
								<p>
									Always decreases.
								</p>
								<p>
									<a class="btn" href="#">View More »</a>
								</p>
							</li>
							<li>
								<h4>
									fundC - TICKER
								</h4>
								<p>
									Always keeps the same.
								</p>
								<p>
									<a class="btn" href="#">View More »</a>
								</p>
							</li>
							<li>
								<h4>
									fundD - TICKER
								</h4>
								<p>
									-.-
								</p>
								<p>
									<a class="btn" href="#">View More »</a>
								</p>
							</li>
						</ol>
	  				</div>


					<div class="col-sm-6 col-md-8"  id="detail-section">
							<h3>
								Detail Information of Fund A - Ticker
							</h3>
							<dl>
								<dt>
									Description of Fund.
								</dt>
								<dt>
									Stock A
								</dt>
								<dd>
									...
								</dd>
								<dt>
									Stock B
								</dt>
								<dd>
									...
								</dd>
								<dt>
									Stock C
								</dt>
								<dd>
									...
								</dd>
							</dl>
							<table class="table">
								<thead>
									<tr>
										<th>
											Date
										</th>
										<th>
											Price
										</th>
										<th>
											Status
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											04/04/2013
										</td>
										<td>
											15.00
										</td>
										<td>
											Up
										</td>
									</tr>
									<tr >
										<td>
											03/04/2013
										</td>
										<td>
											15.00
										</td>
										<td>
											Up
										</td>
									</tr>
									<tr >
										<td>
											02/04/2013
										</td>
										<td>
											10.00
										</td>
										<td>
											Down
										</td>
									</tr>
									<tr >
										<td>
											01/04/2013
										</td>
										<td>
											11.00
										</td>
										<td>
											Up
										</td>
									</tr>
									<tr >
										<td>
											12/04/2012
										</td>
										<td>
											10.00
										</td>
										<td>
											-
										</td>
									</tr>
								</tbody>
							</table>
				  </div>		
				</div>



				

				<nav>
	  				<ul class="pagination">
	    				<li class="disabled">
	    					<a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
	    				</li>
	    				<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
	    				<li><a href="#">2</a></li>
	   					<li><a href="#">3</a></li>
	    				<li><a href="#">4</a></li>
	    				<li><a href="#">5</a></li>
	    				<li>
	      					<a href="#" aria-label="Next">
	        					<span aria-hidden="true">&raquo;</span>
	      					</a>
	    				</li>
	  				</ul>
				</nav>

			</div>
		</div>
	</div><jsp:include page="template-bottom.jsp" />
