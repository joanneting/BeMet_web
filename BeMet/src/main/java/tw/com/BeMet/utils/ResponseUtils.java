package tw.com.BeMet.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {
    private static final Integer RESPONSE_SUCCESS = 1;
    private final Integer RESPONSE_ERROR = 2;
    private final Integer RESPONSE_NODATA = 3;
    private final Integer RESPONSE_NOLOGIN = 4;

    private ResponseUtils() {

    }

    public static void response(HttpServletResponse response, int status, boolean isSuccess, String errorCode, String message) throws IOException {
        ResponseUtils.response(response, status, isSuccess, errorCode, message, null);
    }

    public static void response(HttpServletResponse response, int status, boolean isSuccess, String errorCode, String message, JsonNode data) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(status);
        response.resetBuffer();
        response.getWriter().println(getResultJsonString(isSuccess, errorCode, message, data));
        response.flushBuffer();
    }

    public static String getResultJsonString(boolean isSuccess, String errorCode, String message) throws IOException {
        return getResultJsonString(isSuccess, errorCode, message, null);
    }

    public static String getResultJsonString(boolean isSuccess, String errorCode, String message, JsonNode data) throws IOException {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        result.put("result", isSuccess);
//        result.put("errorCode", errorCode);
        result.put("message", message);
        if (data == null) {
            if (data instanceof ObjectNode) {
                result.putObject("data");
            } else {
                result.putArray("data");
            }
        } else {
            result.set("data", data);
        }
        return o.writeValueAsString(result);
    }
}
