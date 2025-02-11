package vn.vothien.ghost.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import vn.vothien.ghost.domain.response.RestResponse;

public class GlobalException {
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<RestResponse<Object>> handleIdException(IdInvalidException idException) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(idException.getMessage());
        res.setMessage("IdInvalidException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
