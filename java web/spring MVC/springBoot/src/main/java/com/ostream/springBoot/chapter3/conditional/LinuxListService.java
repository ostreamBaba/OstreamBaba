package com.ostream.springBoot.chapter3.conditional;

/**
 * @Create by ostreamBaba on 18-4-9
 * @描述
 */
public class LinuxListService implements ListService{

    @Override
    public String showListCmd() {
        return "ls";
    }
}
