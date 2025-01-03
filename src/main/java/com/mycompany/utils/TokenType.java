/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utils;

/**
 *
 * @author bugsbunny
 */
public enum TokenType {
    KEYWORD,
    IDENTIFIER,
    ACCESS_MODIFIER,
    STRING_LITERAL,
    NUMBER_LITERAL,
    CHAR_LITERAL,
    DELIMETER,
    OPERATOR,
    BRACKET,
    COMPARATOR,
    ASSIGNMENT,
    COMMENT;
    
    @Override
    public String toString() {
        return name().toLowerCase(); 
    }
}
