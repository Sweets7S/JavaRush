package com.game.exceptinons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Sweets
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException{
}
