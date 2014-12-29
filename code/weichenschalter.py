# -*- coding: utf-8 -*-

import RPi.GPIO as GPIO
import time
import sys

GPIO.setmode(GPIO.BCM)
GPIO.setup(18,GPIO.OUT)
GPIO.setup(23,GPIO.IN, pull_up_down=GPIO.PUD_UP)

hz = 50
p = GPIO.PWM(18,hz)
p.start(0)

dcMin = 0.3 #ms
dcMax = 2.4 #ms

linksGrad = 50
rechtsGrad = 120

def knopfHandler(channel) :
        wert = GPIO.input(channel)
        if (wert == GPIO.HIGH) :
                schalteWeiche(linksGrad)
        else :
                schalteWeiche(rechtsGrad)


def schalteWeiche(grad) :
        ms = grad / 180.0 * (dcMax - dcMin) + dcMin
        prozent = (ms/(1.0/hz)/1000)*100
        print '%f°' % (grad,)
        print '%fms' % (ms,)
        print '%f%%' % (prozent,)
        print '----'
        p.ChangeDutyCycle(prozent)
        time.sleep(0.500)
        p.ChangeDutyCycle(0)

try:
        schalteWeiche(linksGrad)
        GPIO.add_event_detect(23, GPIO.BOTH, callback=knopfHandler, bouncetime=500)
        while True:
                time.sleep(0.250)


except KeyboardInterrupt:
        p.stop()
        GPIO.cleanup()

