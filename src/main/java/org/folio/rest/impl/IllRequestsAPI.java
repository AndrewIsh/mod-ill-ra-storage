package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.folio.rest.annotations.Validate;
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
  private static final Logger log = LogManager.getLogger();

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
      "ill_request",
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
      "ill_request",
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
      "ill_request",
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
      "ill_request",
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
      "ill_request",
      id,
      okapiHeaders,
      vertxContext,
      DeleteIllRaStorageRequestsByRequestIdResponse.class,
      asyncResultHandler
    );
  }
}
