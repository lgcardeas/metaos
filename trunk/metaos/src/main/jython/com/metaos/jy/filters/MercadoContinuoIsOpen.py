from com.metaos.datamgt import *
from java.util import *

##
## Filters for open hours for M.C.
##
class MercadoContinuoIsOpen(Filter):
    def filter(self, when, symbol, values):
        minute = when.get(Calendar.HOUR_OF_DAY)*60 + when.get(Calendar.MINUTE)
        minute = int(minute)
        return minute<=1056 and minute>=540
