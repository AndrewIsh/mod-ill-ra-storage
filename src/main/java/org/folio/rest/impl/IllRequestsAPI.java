package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.folio.rest.jaxrs.model.*;
import org.folio.rest.jaxrs.resource.IllRaStorage;
import org.folio.rest.persist.PgUtil;
import org.folio.util.StringUtil;

import javax.ws.rs.core.Response;
import java.util.Map;

public class IllRequestsAPI implements IllRaStorage {
  private static final String ILL_SUBMISSION_TABLE = "ill_submission";
  private static final String SUBMISSION_STATUS_TABLE = "submission_status";
  private static final String ILL_REQUEST_TABLE = "ill_request";
  private static final String ILL_SUPPLIER_MESSAGE_TABLE = "ill_supplier_message";

  @Override
  public void getIllRaStorageRequestsMessagesByRequestId(String requestId, int offset, int limit, String query, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    String messageQuery = String.format("requestId == %s", StringUtil.cqlEncode(requestId));
    PgUtil.get(ILL_SUPPLIER_MESSAGE_TABLE, SaMessage.class, SaMessages.class, messageQuery, offset, limit, okapiHeaders, vertxContext, GetIllRaStorageMessagesResponse.class, asyncResultHandler);
  }

  @Override
  public void postIllRaStorageRequestsMessagesByRequestId(String requestId, String lang, SaMessage entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
  }

  @Override
  public void getIllRaStorageSubmissions(int offset, int limit, String query, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.get(ILL_SUBMISSION_TABLE, Submission.class, Submissions.class, query, offset, limit, okapiHeaders, vertxContext, GetIllRaStorageSubmissionsResponse.class, asyncResultHandler);
  }

  @Override
  public void postIllRaStorageSubmissions(String lang, Submission entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.post(ILL_SUBMISSION_TABLE, entity, okapiHeaders, vertxContext, PostIllRaStorageSubmissionsResponse.class, asyncResultHandler);
  }

  @Override
  public void getIllRaStorageSubmissionsBySubmissionId(String submissionId, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.getById(ILL_SUBMISSION_TABLE, Submission.class, submissionId, okapiHeaders, vertxContext, GetIllRaStorageSubmissionsBySubmissionIdResponse.class, asyncResultHandler);
  }

  @Override
  public void putIllRaStorageSubmissionsBySubmissionId(String submissionId, String lang, Submission entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.put(ILL_SUBMISSION_TABLE, entity, submissionId, okapiHeaders, vertxContext, PutIllRaStorageRequestsByRequestIdResponse.class, asyncResultHandler);
  }

  @Override
  public void deleteIllRaStorageSubmissionsBySubmissionId(String submissionId, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.deleteById(ILL_SUBMISSION_TABLE, submissionId, okapiHeaders, vertxContext, DeleteIllRaStorageSubmissionsBySubmissionIdResponse.class, asyncResultHandler);
  }

  @Override
  public void getIllRaStorageSubmissionsRequestsBySubmissionId(String submissionId, int offset, int limit, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    try {
      String submissionQuery = String.format("submissionId == %s", StringUtil.cqlEncode(submissionId));
      PgUtil.get(ILL_REQUEST_TABLE, Request.class, Requests.class, submissionQuery, offset, limit, okapiHeaders, vertxContext, GetIllRaStorageSubmissionsRequestsBySubmissionIdResponse.class, asyncResultHandler);
    } catch(Exception e) {
      asyncResultHandler.handle(Future.succeededFuture(GetIllRaStorageSubmissionsRequestsBySubmissionIdResponse.respond500WithTextPlain(e.getMessage())));
    }
  }

  @Override
  public void postIllRaStorageMessages(String lang, SaMessage entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    // Receive a Supplying Agency Message a store it
    PgUtil.post(ILL_SUPPLIER_MESSAGE_TABLE, entity, okapiHeaders, vertxContext, PostIllRaStorageMessagesResponse.class, asyncResultHandler);
  }

  @Override
  public void getIllRaStorageMessages(String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
  }

  @Override
  public void postIllRaStorageSubmissionsRequestsBySubmissionId(String submissionId, String lang, Request entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
  }

  @Override
  public void getIllRaStorageSubmissionStatuses(int offset, int limit, String query, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.get(SUBMISSION_STATUS_TABLE, SubmissionStatus.class, SubmissionStatuses.class, query, offset, limit, okapiHeaders, vertxContext, GetIllRaStorageSubmissionStatusesResponse.class, asyncResultHandler);
  }

  @Override
  public void postIllRaStorageSubmissionStatuses(String lang, SubmissionStatus entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.post(SUBMISSION_STATUS_TABLE, entity, okapiHeaders, vertxContext, PostIllRaStorageSubmissionStatusesResponse.class, asyncResultHandler);
  }

  @Override
  public void getIllRaStorageSubmissionStatusesByStatusId(String statusId, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.getById(SUBMISSION_STATUS_TABLE, SubmissionStatus.class, statusId, okapiHeaders, vertxContext, GetIllRaStorageSubmissionStatusesByStatusIdResponse.class, asyncResultHandler);
  }

  @Override
  public void putIllRaStorageSubmissionStatusesByStatusId(String statusId, String lang, SubmissionStatus entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.put(SUBMISSION_STATUS_TABLE, entity, statusId, okapiHeaders, vertxContext, PutIllRaStorageSubmissionStatusesByStatusIdResponse.class, asyncResultHandler);
  }

  @Override
  public void deleteIllRaStorageSubmissionStatusesByStatusId(String statusId, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.deleteById(SUBMISSION_STATUS_TABLE, statusId, okapiHeaders, vertxContext, DeleteIllRaStorageSubmissionStatusesByStatusIdResponse.class, asyncResultHandler);
  }

  @Override
  public void getIllRaStorageRequests(int offset, int limit, String query, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.get(ILL_REQUEST_TABLE, Request.class, Requests.class, query, offset, limit, okapiHeaders, vertxContext, GetIllRaStorageRequestsResponse.class, asyncResultHandler);
  }

  @Override
  public void postIllRaStorageRequests(String lang, Request entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.post(ILL_REQUEST_TABLE, entity, okapiHeaders, vertxContext, PostIllRaStorageRequestsResponse.class, asyncResultHandler);
  }

  @Override
  public void getIllRaStorageRequestsByRequestId(String requestId, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.getById(ILL_REQUEST_TABLE, Request.class, requestId, okapiHeaders, vertxContext, GetIllRaStorageRequestsByRequestIdResponse.class, asyncResultHandler);
  }

  @Override
  public void putIllRaStorageRequestsByRequestId(String requestId, String lang, Request entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.put(ILL_REQUEST_TABLE, entity, requestId, okapiHeaders, vertxContext, PutIllRaStorageRequestsByRequestIdResponse.class, asyncResultHandler);
  }

  @Override
  public void deleteIllRaStorageRequestsByRequestId(String requestId, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.deleteById(ILL_REQUEST_TABLE, requestId, okapiHeaders, vertxContext, DeleteIllRaStorageRequestsByRequestIdResponse.class, asyncResultHandler);
  }
}
