import math

def main():
	print('please select the function to run')
	print('1: vert(k,n)')
	print('2: vertices(n)')
	print('3: dist(p1, p2)')
	print('4: perimeter(verts)')
	print('5: heron(p1, p2, p3)')
	print('6: area(vertices(n))')
	print('7: main()')
	print('8: exit')
	ui = eval(input('...: '))

	if ui == 1:
		n = 0
		n = eval(input('please enter the total number of vertices of the shape: '))
		k = eval(input('please enter the nunmber vertice you would like the location of: '))
		print(vert(k, n))
		main()
	elif ui == 2:
		n = 0
		n = eval(input('please enter the number of vertices the regular polygon has: '))
		print('sides of shape you entered ' + str(len(vertices(n))))
		main()
	elif ui == 3:
		x1 = eval(input("please enter the x value of the first point: "))
		y1 = eval(input("please enter the y value of the first point: "))
		x2 = eval(input("please enter the x value of the second point: "))
		y2 = eval(input("please enter the y value of the second point: "))
		p1 = (x1,y1)
		p2 = (x2,y2)
		D = dist(p1,p2)
		print('the distance between p1:' + str(p1) + ' and p2:' + str(p2) + ' is ' + str(D))
		main()
	elif ui == 4:
		n = 0
		n = eval(input('how many sides are on the shape you want the perimeter of?: '))
		verts = vertices(n)
		P = perimeter(verts)
		print('The perimeter is: ' + str(P))
		main()
	elif ui == 5:
		triangle = vertices(3)
		print('points of a triangle: ' + str(triangle[0]) + ', ' + str(triangle[1]) + ', ' + str(triangle[2] ))
		A = heron(triangle[0], triangle[1], triangle[2])
		print('Area of a traingle is: ' + str(A))
		main()
	elif ui == 6:
		n = 0
		n = eval(input('How many sided regular polygon d oyou want the area of?: '))
		A = area(vertices(n))
		print('The area of a(n) '+ str(n) +' reg polygon is ' + str(A))
		main()
	elif ui == 7:
		verts = ()
		for i in range(1004):
			if (i%100) == 3:
				perm = perimeter(vertices(i))
				A = area(vertices(i))
				p2o4a = ((perm**2)/(4.0*A))
				print('n : ' + str(i))
				print('Perimeter : '+ str(perm))
				print('Area : '+ str(A))
				print('perimeter^2/4*Area = ' + str(p2o4a))
		main()
	elif ui > 7 or ui < 1:
		print('Good bye')
		


def vert(k, n):
	pi2 = math.pi*2
	kon = k/n
	x = math.cos(pi2*kon)
	y = math.sin(pi2*kon)
	loca = (x, y)
	return loca

def vertices(n):
	verts = []
	for i in range(n):
		verts.append(vert(i,n))
	return verts

def dist(p1, p2):
	x1 = p1[0]
	x2 = p2[0]
	y1 = p1[1]
	y2 = p2[1]
	xdif = x1 - x2
	ydif = y1 - y2
	xDSqu = xdif**2
	yDSqu = ydif**2
	difSum = xDSqu + yDSqu
	distance = ((difSum)**(0.5))
	return distance
		
def perimeter(verts):
	peri = 0
	for i in range(len(verts)):
		if i == (len(verts) - 1):
			peri += dist(verts[len(verts)-1],verts[0])
		else:
			peri += dist(verts[i],verts[i+1])
	return peri

def heron(p1, p2, p3):
	l1 = dist(p1,p2)
	l2 = dist(p2,p3)
	l3 = dist(p3,p1)
	s = ((l1+l2+l3)/2.0)
	A = ((s*((s-l1)*(s-l2)*(s-l3)))**(0.5))
	return A

def area(verts):
	if type(verts) == type(1):
		verts = vertices(n)
	#elif type(verts) == type((1,2)):
	#	verts = verts
	Area = 0
	i = 0
	while i < (len(verts)-1):
		Area += heron(verts[i],verts[i+1],(0,0))
		#print('Area: ' + str(Area))
		i += 1
	Area += heron(verts[len(verts)-1],verts[0],(0,0))
	return Area


if __name__ == '__main__':
	main()
