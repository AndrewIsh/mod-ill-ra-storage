package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.rest.annotations.Validate;
import org.folio.rest.jaxrs.model.IllStatus;
import org.folio.rest.jaxrs.model.IllStatuses;
import org.folio.rest.jaxrs.model.Request;
import org.folio.rest.jaxrs.model.Requests;
import org.folio.rest.jaxrs.resource.IllRaStorage;
import org.folio.rest.persist.PgUtil;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.ws.rs.core.Response;
import java.util.Map;

public class IllRequestsAPI implements IllRaStorage {
  private static final String ILL_REQUEST_TABLE = "ill_request";
  private static final String ILL_STATUS_TABLE = "ill_status";

  @Validate
  @Override
  public void getIllRaStorageRequests(
    @Min(0L) @Max(2147483647L) int offset,
    @Min(0L) @Max(2147483647L) int limit,
    String query,
    @Pattern(regexp = "[a-zA-Z]{2}") String lang,
    Map<String, String> okapiHeaders,
    Handler<AsyncResult<Response>> asyncResultHandler,
    Context vertxContext
  ) {
    PgUtil.get(
      ILL_REQUEST_TABLE,
      Request.class,
      Requests.class,
      query,
      offset,
      limit,
      okapiHeaders,
      vertxContext,
      GetIllRaStorageRequestsResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void postIllRaStorageRequests(
    @Pattern(regexp = "[a-zA-Z]{2}") String lang,
    Request request,
    Map<String, String> okapiHeaders,
    Handler<AsyncResult<Response>> asyncResultHandler,
    Context vertxContext
  ) {
    PgUtil.post(
      ILL_REQUEST_TABLE,
      request,
      okapiHeaders,
      vertxContext,
      PostIllRaStorageRequestsResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void getIllRaStorageRequestsByRequestId(
    String id,
    @Pattern(regexp = "[a-zA-Z]{2}") String lang,
    Map<String, String> okapiHeaders,
    Handler<AsyncResult<Response>> asyncResultHandler,
    Context vertxContext
  ) {
    PgUtil.getById(
      ILL_REQUEST_TABLE,
      Request.class,
      id,
      okapiHeaders,
      vertxContext,
      GetIllRaStorageRequestsByRequestIdResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void putIllRaStorageRequestsByRequestId(
    String id,
    @Pattern(regexp = "[a-zA-Z]{2}") String lang,
    Request request,
    Map<String, String> okapiHeaders,
    Handler<AsyncResult<Response>> asyncResultHandler,
    Context vertxContext
  ) {
    PgUtil.put(
      ILL_REQUEST_TABLE,
      request,
      id,
      okapiHeaders,
      vertxContext,
      PutIllRaStorageRequestsByRequestIdResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void deleteIllRaStorageRequestsByRequestId(
    String id,
    @Pattern(regexp = "[a-zA-Z]{2}") String lang,
    Map<String, String> okapiHeaders,
    Handler<AsyncResult<Response>> asyncResultHandler,
    Context vertxContext
  ) {
    PgUtil.deleteById(
      ILL_REQUEST_TABLE,
      id,
      okapiHeaders,
      vertxContext,
      DeleteIllRaStorageRequestsByRequestIdResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void getIllRaStorageIllStatuses(int offset, int limit, String query, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.get(
      ILL_STATUS_TABLE,
      IllStatus.class,
      IllStatuses.class,
      query,
      offset,
      limit,
      okapiHeaders,
      vertxContext,
      GetIllRaStorageIllStatusesResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void postIllRaStorageIllStatuses(String lang, IllStatus status, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.post(
      ILL_STATUS_TABLE,
      status,
      okapiHeaders,
      vertxContext,
      PostIllRaStorageIllStatusesResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void getIllRaStorageIllStatusesByStatusId(String id, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.getById(
      ILL_STATUS_TABLE,
      IllStatus.class,
      id,
      okapiHeaders,
      vertxContext,
      GetIllRaStorageIllStatusesByStatusIdResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void putIllRaStorageIllStatusesByStatusId(String id, String lang, IllStatus status, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.put(
      ILL_STATUS_TABLE,
      status,
      id,
      okapiHeaders,
      vertxContext,
      PutIllRaStorageIllStatusesByStatusIdResponse.class,
      asyncResultHandler
    );
  }

  @Validate
  @Override
  public void deleteIllRaStorageIllStatusesByStatusId(String id, String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) {
    PgUtil.deleteById(
      ILL_STATUS_TABLE,
      id,
      okapiHeaders,
      vertxContext,
      DeleteIllRaStorageIllStatusesByStatusIdResponse.class,
      asyncResultHandler
    );
  }
}
