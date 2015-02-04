# -*- coding: utf-8 -*-
from abc import ABCMeta, abstractmethod

class Beverage(object):
    __metaclass__ = ABCMeta #추상클래스 선언
    
    def __init__(self):
        self.description = "Null"
        
    def get_description(self):
        return self.description
        
    @abstractmethod
    def cost(self): pass
    
class Americano(Beverage):
    def __init__(self):
        self.description = "Americano"
        
    def cost(self):
        return 1.99
        
class CondimentDecorator(Beverage):
    __metaclass__ = ABCMeta
    @abstractmethod
    def get_description(self): pass
    
class Soy(CondimentDecorator):
    def __init__(self, beverage):
        self.beverage = beverage
        
    def get_description(self):
        return self.beverage.get_description() + ", Soy"
        
    def cost(self):
        return self.beverage.cost() + 0.5        
        

    