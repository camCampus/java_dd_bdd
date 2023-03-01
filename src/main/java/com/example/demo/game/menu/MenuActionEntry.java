package com.example.demo.game.menu;

 public interface MenuActionEntry {

    public  void apply(Menu menu) ;
    public  String getLabel();

    public boolean isVisible();
}
