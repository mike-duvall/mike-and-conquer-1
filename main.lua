
function love.load() ---loads all we need in game
   if arg[#arg] == "-debug" then require("mobdebug").start() end  
   love.graphics.setColor(0, 0, 0, 225)
   love.graphics.setBackgroundColor(225, 153, 0)

end

function love.draw() ---function to display/draw content to screen

   love.graphics.circle("fill", 200, 300, 50, 50)

---draw rectangle with parameters(mode, x-pos, y-pos, width, height)

   love.graphics.rectangle("fill", 300, 300, 100, 100)

---draw an arc with parameters(mode,x-pos,y-pos,radius,angle1,angle2)

   love.graphics.arc("fill", 450, 300, 100, math.pi/5, math.pi/2)
end


