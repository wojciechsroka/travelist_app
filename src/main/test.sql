select count(idloc) as claimedSpaces, oe.garageCode  as creationIdLocation, TRIM(ExtractValue(oe.asXml, '/ds/garageLocationShortName')) as garageLocationShortName,
					TRIM(ExtractValue(oe.asXml, '/ds/nominalCapacity')) as nominalCapacity from
					organizationalentity oe
					left join (select i2.creationIdLocation as idloc from inventory i2 where i2.status=1 or i2.status=4) as idloc on 
						(idloc = oe.garageCode or  
						(idloc != oe.garageCode and  (TRIM(ExtractValue(oe.asXml, '/ds/vehicleLocation/location')) = TRIM(ExtractValue(oe.asXml, '/ds/vehicleLocation/location')))))
                    where
					oe.entityType = 'G'
					group by oe.garageCode;