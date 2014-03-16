package com.tech.obfil.exception;

public class ErrorCodeEnum {

	public static final BaseError APPLICATION_ERROR_20001 = new FileObjError("APPLICATION_ERROR_20001","Application Validation error.","400"); // parameter is not as per expecation
	public static final BaseError APPLICATION_ERROR_20002 = new FileObjError("APPLICATION_ERROR_20002","Application Uninitialized error.","500"); // Object is null or not initialized
	public static final BaseError APPLICATION_ERROR_20003 = new FileObjError("APPLICATION_ERROR_20003","Data is corrupted","500"); //IO Exception
	public static final BaseError APPLICATION_ERROR_20004 = new FileObjError("APPLICATION_ERROR_20004","System resource access error.","404"); //Object was not found
	public static final BaseError APPLICATION_ERROR_20005 = new FileObjError("APPLICATION_ERROR_20005","System can not create entity.","422"); //Create Exception
	public static final BaseError APPLICATION_ERROR_20006 = new FileObjError("APPLICATION_ERROR_20006","Request parameter is not valid","400"); //Bad Request
	public static final BaseError APPLICATION_ERROR_20007 = new FileObjError("APPLICATION_ERROR_20007","CIP Provider not found","422"); //Provider resource not found
	

	/*
200	OK	Success!
304	Not Modified	There was no new data to return.
400	Bad Request	The request was invalid. An accompanying error message will explain why. This is the status code will be returned during version 1.0 rate limiting. In API v1.1, a request without authentication is considered invalid and you will get this response.
401	Unauthorized	Authentication credentials were missing or incorrect.
403	Forbidden	The request is understood, but it has been refused or access is not allowed. An accompanying error message will explain why. This code is used when requests are being denied due to update limits.
404	Not Found	The URI requested is invalid or the resource requested, such as a user, does not exists. Also returned when the requested format is not supported by the requested method.
406	Not Acceptable	Returned by the Search API when an invalid format is specified in the request.
420	Enhance Your Calm	Returned by the version 1 Search and Trends APIs when you are being rate limited.
422	Unprocessable Entity	Returned when an image uploaded to POST account/update_profile_banner is unable to be processed.
429	Too Many Requests	Returned in API v1.1 when a request cannot be served due to the application's rate limit having been exhausted for the resource. See Rate Limiting in API v1.1.
500	Internal Server Error	Something is broken. Please post to the group so the WebsiteX team can investigate.
502	Bad Gateway	WebsiteX is down or being upgraded.
503	Service Unavailable	The WebsiteX servers are up, but overloaded with requests. Try again later.
504	Gateway timeout	The WebsiteX servers are up, but the request couldn't be serviced due to some failure within our stack. Try again later.
32	Could not authenticate you	Your call could not be completed as dialed.
34	Sorry, that page does not exist	Corresponds with an HTTP 404 - the specified resource was not found.
88	Rate limit exceeded	The request limit for this resource has been reached for the current rate limit window.
89	Invalid or expired token	The access token used in the request is incorrect or has expired. Used in API v1.1
130	Over capacity	Corresponds with an HTTP 503 - WebsiteX is temporarily over capacity.
131	Internal error	Corresponds with an HTTP 500 - An unknown internal error occurred.
135	Could not authenticate you	Corresponds with a HTTP 401 - it means that your oauth_timestamp is either ahead or behind our acceptable range
215	Bad authentication data	Typically sent with 1.1 responses with HTTP code 400. The method requires authentication but it was not presented or was wholly invalid.
http://msdn.microsoft.com/en-us/library/windowsazure/dd179357.aspx
ConditionNotMet

Not Modified (304)

The condition specified in the conditional header(s) was not met for a read operation.

MissingRequiredHeader

Bad Request (400)

A required HTTP header was not specified.

MissingRequiredXmlNode

Bad Request (400)

A required XML node was not specified in the request body.

UnsupportedHeader

Bad Request (400)

One of the HTTP headers specified in the request is not supported.

UnsupportedXmlNode

Bad Request (400)

One of the XML nodes specified in the request body is not supported.

InvalidHeaderValue

Bad Request (400)

The value provided for one of the HTTP headers was not in the correct format.

InvalidXmlNodeValue

Bad Request (400)

The value provided for one of the XML nodes in the request body was not in the correct format.

MissingRequiredQueryParameter

Bad Request (400)

A required query parameter was not specified for this request.

UnsupportedQueryParameter

Bad Request (400)

One of the query parameters specified in the request URI is not supported.

InvalidQueryParameterValue

Bad Request (400)

An invalid value was specified for one of the query parameters in the request URI.

OutOfRangeQueryParameterValue

Bad Request (400)

A query parameter specified in the request URI is outside the permissible range.

RequestUrlFailedToParse

Bad Request (400)

The url in the request could not be parsed.

InvalidUri

Bad Request (400)

The requested URI does not represent any resource on the server.

InvalidHttpVerb

Bad Request (400)

The HTTP verb specified was not recognized by the server.

EmptyMetadataKey

Bad Request (400)

The key for one of the metadata key-value pairs is empty.

InvalidXmlDocument

Bad Request (400)

The specified XML is not syntactically valid.

Md5Mismatch

Bad Request (400)

The MD5 value specified in the request did not match the MD5 value calculated by the server.

InvalidMd5

Bad Request (400)

The MD5 value specified in the request is invalid. The MD5 value must be 128 bits and Base64-encoded.

OutOfRangeInput

Bad Request (400)

One of the request inputs is out of range.

InvalidAuthenticationInfo

Bad Request (400)

The authentication information was not provided in the correct format. Verify the value of Authorization header.

InvalidInput

Bad Request (400)

One of the request inputs is not valid.

InvalidMetadata

Bad Request (400)

The specified metadata is invalid. It includes characters that are not permitted.

MetadataTooLarge

Bad Request (400)

The size of the specified metadata exceeds the maximum size permitted.

AuthenticationFailed

Forbidden (403)

The server failed to authenticate the request. Verify that the value of Authorization header is formed correctly and includes the signature.

ResourceNotFound

Not Found (404)

The specified resource does not exist.

AccountIsDisabled

Forbidden (403)

The specified account is disabled.

UnsupportedHttpVerb

Method Not Allowed (405)

The resource doesn't support the specified HTTP verb.

AccountAlreadyExists

Conflict (409)

The specified account already exists.

AccountBeingCreated

Conflict (409)

The specified account is in the process of being created.

InsufficientAccountPermissions

Forbidden (403)

The account being accessed does not have sufficient permissions to execute this operation.

MissingContentLengthHeader

Length Required (411)

The Content-Length header was not specified.

ConditionNotMet

Precondition Failed (412)

The condition specified in the conditional header(s) was not met for a write operation.

MultipleConditionHeadersNotSupported

Bad Request (400)

Multiple condition headers are not supported.

RequestBodyTooLarge

Request Entity Too Large (413)

The size of the request body exceeds the maximum size permitted.

InvalidRange

Requested Range Not Satisfiable (416)

The range specified is invalid for the current size of the resource.

InternalError

Internal Server Error (500)

The server encountered an internal error. Please retry the request.

OperationTimedOut

Internal Server Error (500)

The operation could not be completed within the permitted time.

ServerBusy

Service Unavailable (503)

The server is currently unable to receive requests. Please retry your request.


<?xml version="1.0" encoding="UTF-8"?>
<errors>
  <error code="34">Sorry, that page does not exist</error>
</errors>
	 */
	
	
}
